import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class AzurePredictionTest : StringSpec({
    val json = PredictionJson.predictionOne
    val result = AzurePredictionBuilder.build(json)!!

    "#highPredictions filters downs to 15 highest predictions" {
        result.predictions.size shouldBe 35
        result.highPredictions().size shouldBe 20
    }

    "#toWindow creates a window with dice in expected positions" {
        val window = result.toWindow()
        result.highPredictions().minWith(compareBy( { it.boundingBox.top }, { it.boundingBox.left }))!!
          .tagName shouldBe window.rows()[0][0].faceValue
    }
})