import kotlin.reflect.KProperty1

sealed class PublicObjective {
    abstract val points: Int
    abstract val name: String
    protected abstract fun count(window: Window): Int
    fun solve(window: Window): Int = count(window) * points

    protected fun fold(rows: List<List<Die>>, faceAccessor: KProperty1<Die, Face>? = null, colorAccessor: KProperty1<Die, Color>? = null): Int {
        assert(faceAccessor != null || colorAccessor != null)

        return rows.fold(0) { acc, dice ->
            val set = dice.map { faceAccessor?.invoke(it) ?: colorAccessor?.invoke(it) }.toSet()
            if (set.size == dice.size) acc + 1 else acc
        }
    }

    protected class Counter<T> {
        fun count(rows: List<List<Die>>, accessorFn: (Die) -> T): Int {
            return rows.fold(0) {acc, dice ->
                val set = dice.map { accessorFn(it) }.toSet()
                if (set.size == dice.size) acc + 1 else acc
            }
        }
    }
}

class RowShadeVariety : PublicObjective() {
    override val name: String = "Row Shade Variety"
    override val points: Int = 5

    override fun count(window: Window) = Counter<Face>().count(window.rows(), Die::faceValue)
}

class ColumnShadeVariety : PublicObjective() {
    override val name: String = "Column Shade Variety"
    override val points: Int = 4

    override fun count(window: Window) = Counter<Face>().count(window.columns(), Die::faceValue)
}

abstract class ShadePair(private val match: List<Face>): PublicObjective() {
    override val points: Int = 2
    override fun count(window: Window): Int {
       val matches = window.groupByValue().filter {
           match.contains(it.key)
       }

        return when (matches.size) {
            0 -> 0
            1 -> 0
            else -> matches.minBy { it.value }?.value ?: 0
        }
    }
}

class LightShades : ShadePair(match = listOf(Face.ONE, Face.TWO)) {
    override val name: String = "Light Shades"
}

class MediumShades : ShadePair(match = listOf(Face.THREE, Face.FOUR)) {
    override val name: String = "Medium Shades"
}

class DeepShades : ShadePair(match = listOf(Face.FIVE, Face.SIX)) {
    override val name: String = "Deep Shades"
}
