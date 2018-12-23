import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class DiceTest : StringSpec({
    val json = DiceJson.diceColorOne
    val result = DiceBuilder.build(json)!!

    "it builds" {
        result.dice.size shouldBe 4
        result.dice.all { it.size == 5 } shouldBe true
        result.dice[0][0].color shouldBe Color.YELLOW
        result.dice[0][0].pips shouldBe Face.ONE
    }
})