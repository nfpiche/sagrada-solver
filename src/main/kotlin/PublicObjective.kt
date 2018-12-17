import kotlin.reflect.KProperty1

sealed class PublicObjective {
    abstract val points: Int
    abstract val name: String
    protected abstract fun count(window: Window): Int
    fun solve(window: Window): Int = count(window) * points
}


abstract class RowVariety<T>(override val accessFn: (Die) -> T) : Counter<T>({ w -> w.rows() }) {
    override val points: Int = 5
}
abstract class ColumnVariety<T>(override val accessFn: (Die) -> T) : Counter<T>({ w -> w.columns() }) {
    override val points: Int = 4
}

abstract class Counter<T>(private val windowFn: (Window) -> List<List<Die>>) : PublicObjective() {
    protected abstract val accessFn: (Die) -> T
    override fun count(window: Window): Int =
            windowFn(window).count {
                it.map { accessFn(it) }.toSet().size == it.size
            }
}

class RowShadeVariety(override val name: String = "Row Shade Variety") : RowVariety<Face>(Die::faceValue)
class RowColorVariety(override val name: String = "Row Color Variety") : RowVariety<Color>(Die::color)
class ColumnShadeVariety(override val name: String = "Column Shade Variety") : ColumnVariety<Face>(Die::faceValue)
class ColumnColorVariety(override val name: String = "Column Color Variety") : ColumnVariety<Color>(Die::color)


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
