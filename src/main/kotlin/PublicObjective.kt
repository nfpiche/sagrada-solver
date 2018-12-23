sealed class PublicObjective {
    abstract val points: Int
    abstract val name: String
    protected abstract fun count(window: Window): Int
    fun solve(window: Window): Int = count(window) * points
    fun img(): String = "${BaseUrls.cloudinaryUrl}/$name.jpg"
}

abstract class WindowVariety<T>(private val windowFn: (Window) -> Map<T, Int>, private val checkSize: Int) : PublicObjective() {
    protected abstract val match: List<T>

    override fun count(window: Window): Int {
        val matches = windowFn(window).filter { match.contains(it.key) }

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
    override fun count(window: Window): Int = windowFn(window).count { it.map { accessFn(it) }.toSet().size == it.size }
}

class RowShadeVariety(override val name: String = "row-shade-variety") : RowVariety<Face>(Die::faceValue)
class RowColorVariety(override val name: String = "row-color-variety") : RowVariety<Color>(Die::color)
class ColumnShadeVariety(override val name: String = "column-shade-variety") : ColumnVariety<Face>(Die::faceValue)
class ColumnColorVariety(override val name: String = "column-color-variety") : ColumnVariety<Color>(Die::color)

abstract class ShadePair(override val match: List<Face>): WindowVariety<Face>({ w -> w.groupByValue() }, 2) {
    override val points: Int = 2
}

class LightShades(override val name: String = "light-shades") : ShadePair(listOf(Face.ONE, Face.TWO))
class MediumShades(override val name: String = "medium-shades") : ShadePair(listOf(Face.THREE, Face.FOUR))
class DeepShades(override  val name: String = "deep-shades") : ShadePair(listOf(Face.FIVE, Face.SIX))

class ColorVariety : WindowVariety<Color>({ w -> w.groupByColor() }, 5) {
    override val name: String = "color-variety"
    override val points: Int = 4
    override val match: List<Color> = Color.values().toList()
}

class ShadeVariety : WindowVariety<Face>({ w -> w.groupByValue() }, 6) {
    override val name: String = "shade-variety"
    override val points: Int = 5
    override val match: List<Face> = Face.values().toList()
}

class ColorDiagonals : PublicObjective() {
    override val name: String = "color-diagonals"
    override val points: Int = 1
    override fun count(window: Window): Int {
        val counts = mutableMapOf<Color, MutableSet<Pair<Int, Int>>>()
        val rows = window.rows()
        for (y in 0..3) {
            for (x in 0..4) {
                val die = rows[y][x]
                counts.getOrPut(die.color) { mutableSetOf() }.plus(x to y)
                window.neighborsFor(x, y, die.color).forEach { counts.getOrPut(it.die.color) { mutableSetOf() }.add(it.position)}
            }
        }

        return counts.values.fold(0) { acc, positions ->
            if (positions.size > 1) acc + positions.size else acc
        }
    }
}