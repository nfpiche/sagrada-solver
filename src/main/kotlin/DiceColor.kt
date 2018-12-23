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

data class Dice(
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

val convertColor = object : Converter {
    override fun canConvert(cls: Class<*>): Boolean = cls == Color::class.java

    override fun toJson(value: Any) = """{}"""

    override fun fromJson(jv: JsonValue): Color {
        return when (jv.string) {
            "red" -> Color.RED
            "blue" -> Color.BLUE
            "green" -> Color.GREEN
            "yellow" -> Color.YELLOW
            "purple" -> Color.PURPLE
            "None" -> Color.NONE
            else -> throw IllegalArgumentException("Unknown color: $jv")
        }
    }
}

@Target(AnnotationTarget.FIELD)
annotation class KlaxonFace

val convertFace = object : Converter {
    override fun canConvert(cls: Class<*>): Boolean = cls == Face::class.java

    override fun toJson(value: Any) = """{}"""

    override fun fromJson(jv: JsonValue): Face {
        return when (jv.int) {
            Face.EMPTY.value -> Face.EMPTY
            Face.ONE.value -> Face.ONE
            Face.TWO.value -> Face.TWO
            Face.THREE.value -> Face.THREE
            Face.FOUR.value -> Face.FOUR
            Face.FIVE.value -> Face.FIVE
            Face.SIX.value -> Face.SIX
            else -> throw IllegalArgumentException("Unknown face: $jv")
        }
    }
}
