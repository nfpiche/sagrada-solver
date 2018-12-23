enum class Color(val color: String ) {
    BLUE("blue"),
    GREEN("green"),
    PURPLE("purple"),
    RED("red"),
    YELLOW("yellow")
}
enum class Face(val value: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6)
}

data class Die(val color: Color, val faceValue: Face) {
    override fun toString(): String = "${color.color}-${faceValue.value}"
    fun img(): String = "${BaseUrls.cloudinaryUrl}/${toString()}.jpg"
}
