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
}

class RowShadeVariety : PublicObjective() {
    override val name: String = "Row Shade Variety"
    override val points: Int = 5

    override fun count(window: Window) = fold(window.rows(), faceAccessor = Die::faceValue)
}

class ColumnShadeVariety : PublicObjective() {
    override val name: String = "Column Shade Variety"
    override val points: Int = 4

    override fun count(window: Window) = fold(window.columns(), faceAccessor = Die::faceValue)
}
