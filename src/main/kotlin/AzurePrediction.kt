import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

object AzurePredictionBuilder {
    fun build(json: String): AzurePrediction? = Klaxon().fieldConverter(KlaxonFace::class, convertFace).parse(json)
}

data class AzurePrediction(
        val id: String,
        val project: String,
        val iteration: String,
        val created: String,
        val predictions: List<Prediction>
) {
    fun highPredictions(): List<Prediction> = predictions.sortedByDescending { it.probability }.take(20)
    fun toWindow(colors: DiceColor): Window {
        val dice = highPredictions().sortedBy { it.boundingBox.top }
                .chunked(5).map { it.sortedBy { it.boundingBox.left } }
                .zip(colors.toColors())
                .map { pair ->
                    pair.first.mapIndexed { index, prediction ->
                        Die(pair.second[index], prediction.tagName)
                    }
                }

        return Window(dice)
    }
}

data class Prediction constructor(
        val probability: Float,
        val tagId: String,
        @KlaxonFace
        val tagName: Face,
        val boundingBox: BoundingBox
)

data class BoundingBox(
        val left: Float,
        val top: Float,
        val width: Float,
        val height: Float
)

@Target(AnnotationTarget.FIELD)
annotation class KlaxonFace

val convertFace = object: Converter {
    override fun canConvert(cls: Class<*>): Boolean = cls == Face::class.java

    override fun toJson(value: Any) = """{}"""

    override fun fromJson(jv: JsonValue): Face {
        return when (jv.string) {
            "one" -> Face.ONE
            "two" -> Face.TWO
            "three" -> Face.THREE
            "four" -> Face.FOUR
            "five" -> Face.FIVE
            "six" -> Face.SIX
            else -> throw IllegalArgumentException("Unknown type: $jv")
        }
    }
}
