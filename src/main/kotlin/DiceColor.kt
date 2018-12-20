import com.beust.klaxon.Klaxon

object DiceColorBuilder {
//    fun build(json: String): DiceColor? = Klaxon().fieldConverter(KlaxonColor::class, convertColor).parse(json)
    fun build(json: String): DiceColor? = Klaxon().parse(json)
}

data class DiceColor constructor(
        val dice: List<List<String>>
) {
    fun toColors(): List<List<Color>> =
        dice.map { it.map { toColor(it) } }

    private fun toColor(s: String): Color {
        return when(s) {
            "red" -> Color.RED
            "blue" -> Color.BLUE
            "green" -> Color.GREEN
            "yellow" -> Color.YELLOW
            "purple" -> Color.PURPLE
            else -> throw IllegalArgumentException("Unknown type: $s")
        }
    }
}

// HOLD FOR FIX IN https://github.com/cbeust/klaxon/issues/145
//@Target(AnnotationTarget.FIELD)
//annotation class KlaxonColor
//
//val convertColor = object: Converter {
//    override fun canConvert(cls: Class<*>): Boolean = cls == Color::class.java
//
//    override fun toJson(value: Any) = """{}"""
//
//    override fun fromJson(jv: JsonValue): Color {
//        return when (jv.string) {
//            "red" -> Color.RED
//            "blue" -> Color.BLUE
//            "green" -> Color.GREEN
//            "yellow" -> Color.YELLOW
//            "purple" -> Color.PURPLE
//            else -> throw IllegalArgumentException("Unknown type: $jv")
//        }
//    }
//}
