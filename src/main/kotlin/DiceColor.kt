import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

object DiceBuilder {
    fun build(json: String): Dice? =
            Klaxon()
                    .fieldConverter(KlaxonColor::class, convertColor)
                    .fieldConverter(KlaxonFace::class, convertFace)
                    .parse(json)
}

data class Dice (
        val dice: List<List<JsonDie>>
) {
    fun forWindow(): List<List<Die>> = dice.map { it.map(JsonDie::toDie) }
}


data class JsonDie constructor(
        @KlaxonFace
        val pips: Face,
        @KlaxonColor
        val color: Color
) {
    fun toDie(): Die = Die(color, pips)
}

@Target(AnnotationTarget.FIELD)
annotation class KlaxonColor

val convertColor = object: Converter {
    override fun canConvert(cls: Class<*>): Boolean = cls == Color::class.java

    override fun toJson(value: Any) = """{}"""

    override fun fromJson(jv: JsonValue): Color {
        return when (jv.string) {
            "red" -> Color.RED
            "blue" -> Color.BLUE
            "green" -> Color.GREEN
            "yellow" -> Color.YELLOW
            "purple" -> Color.PURPLE
            else -> throw IllegalArgumentException("Unknown color: $jv")
        }
    }
}

@Target(AnnotationTarget.FIELD)
annotation class KlaxonFace

val convertFace = object: Converter {
    override fun canConvert(cls: Class<*>): Boolean = cls == Face::class.java

    override fun toJson(value: Any) = """{}"""

    override fun fromJson(jv: JsonValue): Face {
        return when (jv.int) {
            1 -> Face.ONE
            2 -> Face.TWO
            3 -> Face.THREE
            4 -> Face.FOUR
            5 -> Face.FIVE
            6 -> Face.SIX
            else -> throw IllegalArgumentException("Unknown face: $jv")
        }
    }
}
