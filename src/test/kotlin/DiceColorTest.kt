import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class DiceColorTest: StringSpec({
    val json = DiceColorJson.diceColorOne
    val result = DiceColorBuilder.build(json)!!

    "it builds" {
        result.dice.size shouldBe 4
        result.dice.all { it.size == 5 } shouldBe true
    }

    "#toColors converts color strings to Color enum" {
        val colors = result.toColors()
        colors[0][0] shouldBe Color.YELLOW
        colors[0][1] shouldBe Color.RED
    }
})