class GameBoard(private val windows: List<Window>, private val objectives: List<PublicObjective>) {
    fun solve(): Int =
        windows.sumBy { it.solve(objectives) }
}