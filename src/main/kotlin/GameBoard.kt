class GameBoard(private val objectives: List<PublicObjective>) {
    private val windows: MutableList<Window> = mutableListOf()
    fun solve(): Int = windows.sumBy { it.solve(objectives) }
    fun addWindow(window: Window) = windows.plus(window)
}