sealed class PublicObjective {
    abstract val points: Int
    abstract val name: String
    protected abstract fun count(window: Window): Int
    fun solve(window: Window): Int = count(window) * points
}

abstract class WindowVariety<T>(private val windowFn: (Window) -> Map<T, Int>, private val checkSize: Int) : PublicObjective() {
    protected abstract val match: List<T>

    override fun count(window: Window): Int {
        val matches = windowFn(window).filter {
            match.contains(it.key)
        }

        return if (matches.size == checkSize) {
            matches.minBy { it.value }?.value ?: 0
        } else {
            0
        }
    }
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

abstract class ShadePair(override val match: List<Face>): WindowVariety<Face>({ w -> w.groupByValue() }, 2){
    override val points: Int = 2
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

class ColorVariety : WindowVariety<Color>({ w -> w.groupByColor() }, 5) {
    override val name: String = "Color Variety"
    override val points: Int = 4
    override val match: List<Color> = Color.values().toList()
}

class ShadeVariety : WindowVariety<Face>({ w -> w.groupByValue() }, 6) {
    override val name: String = "Shade Variety"
    override val points: Int = 5
    override val match: List<Face> = Face.values().toList()
}
