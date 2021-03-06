class Window(private val dice: List<List<Die>>, private val targetColor: Color) {
    fun solve(objectives: List<PublicObjective>): Int =
        objectives.sumBy { it.solve(this) } + valuesForColor(targetColor)

    fun rows(): List<List<Die>> = dice

    fun columns(): List<List<Die>> {
        val cols: List<ArrayList<Die>> = List(5) { arrayListOf<Die>() }

        for (y in 0 until 5) {
            for (x in 0 until 4) {
                val current = cols[y]
                current += dice[x][y]
            }
        }

        return cols.map { it.toList() }
    }

    fun groupByValue(): Map<Face, Int> = Grouper<Face>().group(dice, Die::faceValue)

    fun groupByColor(): Map<Color, Int> = Grouper<Color>().group(dice, Die::color)

    fun valuesForColor(color: Color): Int = dice.flatten().sumBy {
        if (it.color == color) it.faceValue.value else 0
    }

    fun neighborsFor(x: Int, y: Int, color: Color): List<Neighbor> {
        return listOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1).fold(mutableListOf()) { acc: List<Neighbor>, pair ->
            val xx = x + pair.first
            val yy = y + pair.second
            val tooLow = xx < 0 || yy < 0
            val tooHigh = xx > 4 || yy > 3

            if (tooLow || tooHigh) {
                acc
            } else {
                val die = dice[yy][xx]
                if (die.color == color) acc + Neighbor(xx, yy, die) else acc
            }
        }
    }

    fun display() = println(toString())

    override fun toString(): String =
        with(StringBuilder()) {
            for (row in dice) {
                append(row.joinToString(" ", transform = Die::toString))
                append('\n')
            }
            toString()
        }

    private class Grouper<T> {
        fun group(dice: List<List<Die>>, accessFn: (Die) -> T): Map<T, Int> =
            dice.flatten().groupBy(accessFn).mapValues { it.value.size }
    }
}

data class Neighbor(val xPos: Int, val yPos: Int, val die: Die) {
    val position: Pair<Int, Int> = xPos to yPos
}
