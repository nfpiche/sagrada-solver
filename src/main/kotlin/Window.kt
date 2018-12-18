class Window(private val dice: List<List<Die>>) {
    fun rows(): List<List<Die>> = dice
    fun columns(): List<List<Die>> {
        val cols: List<ArrayList<Die>> = mutableListOf(
                arrayListOf(),
                arrayListOf(),
                arrayListOf(),
                arrayListOf(),
                arrayListOf()
        )

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
    fun valuesForColor(color: Color): Int = dice.map { it.filter { it.color == color } }.flatten().sumBy { it.faceValue.value }

    fun neighborsFor(x: Int, y: Int, color: Color): List<Neighbor> {
        return listOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1).fold(mutableListOf()) { acc: List<Neighbor>, pair ->
            val xx = x + pair.first
            val yy = y + pair.second

            if (xx < 0 || yy < 0 || xx > 4 || yy > 3) {
                acc
            } else {
                val die = dice[yy][xx]
                if (die.color == color) acc + Neighbor(xx, yy, die) else acc
            }
        }
    }

    private class Grouper<T> {
        fun group(dice: List<List<Die>>, accessFn: (Die) -> T): Map<T, Int> {
            return dice.fold(HashMap<T, Int>().withDefault { 0 }) { acc, it ->
                val grouped = it.groupBy { accessFn(it) }
                grouped.entries.forEach {
                    acc.computeIfPresent(it.key) { _, v -> v + it.value.size }
                    acc.computeIfAbsent(it.key) { _ -> it.value.size }
                }
                acc
            }.toMap()
        }
    }
}

data class Neighbor(val xPos: Int, val yPos: Int, val die: Die) {
    val position: Pair<Int, Int> = xPos to yPos
}
