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
}
