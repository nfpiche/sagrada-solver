import factories.WindowFactory
import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class ColumnShadeVarietyTest : StringSpec({
    "#solve should return proper score based on number of matches" {
        forall(
                row(WindowFactory.withUniqueValueColumns(0), 0),
                row(WindowFactory.withUniqueValueColumns(1), 4),
                row(WindowFactory.withUniqueValueColumns(2), 8),
                row(WindowFactory.withUniqueValueColumns(3), 12),
                row(WindowFactory.withUniqueValueColumns(4), 16),
                row(WindowFactory.withUniqueValueColumns(5), 20)
        ) { window, score ->
            ColumnShadeVariety().solve(window) shouldBe score
        }
    }
})

class RowShadeVarietyTest : StringSpec({
    "#solve should return proper score based on number of matches" {
        forall(
                row(WindowFactory.withUniqueValueRows(0), 0),
                row(WindowFactory.withUniqueValueRows(1), 5),
                row(WindowFactory.withUniqueValueRows(2), 10),
                row(WindowFactory.withUniqueValueRows(3), 15),
                row(WindowFactory.withUniqueValueRows(4), 20)
        ) { window, score ->
            RowShadeVariety().solve(window) shouldBe score
        }
    }
})

class ColumnColorVarietyTest : StringSpec({
    "#solve should return proper score based on number of matches" {
        forall(
                row(WindowFactory.withUniqueColorColumns(0), 0),
                row(WindowFactory.withUniqueColorColumns(1), 4),
                row(WindowFactory.withUniqueColorColumns(2), 8),
                row(WindowFactory.withUniqueColorColumns(3), 12),
                row(WindowFactory.withUniqueColorColumns(4), 16),
                row(WindowFactory.withUniqueColorColumns(5), 20)
        ) { window, score ->
            ColumnColorVariety().solve(window) shouldBe score
        }
    }
})

class RowColorVarietyTest : StringSpec({
    "#solve should return proper score based on number of matches" {
        forall(
                row(WindowFactory.withUniqueColorRows(0), 0),
                row(WindowFactory.withUniqueColorRows(1), 5),
                row(WindowFactory.withUniqueColorRows(2), 10),
                row(WindowFactory.withUniqueColorRows(3), 15),
                row(WindowFactory.withUniqueColorRows(4), 20)
        ) { window, score ->
            RowColorVariety().solve(window) shouldBe score
        }
    }
})

class LightShadesTest : StringSpec({
    "#solve should return proper score pairs of 1 + 2" {
        forall(
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 0), 0),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 1), 2),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 2), 4),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 3), 6),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 4), 8),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 5), 10),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 6), 12),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 7), 14),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 8), 16),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 9), 18),
                row(WindowFactory.withPairs(Face.ONE, Face.TWO, 10), 20)
        ) { window, score ->
            LightShades().solve(window) shouldBe score
        }
    }
})

class MediumShadesTest : StringSpec({
    "#solve should return proper score pairs of 3 + 4" {
        forall(
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 0), 0),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 1), 2),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 2), 4),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 3), 6),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 4), 8),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 5), 10),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 6), 12),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 7), 14),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 8), 16),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 9), 18),
                row(WindowFactory.withPairs(Face.THREE, Face.FOUR, 10), 20)
        ) { window, score ->
            MediumShades().solve(window) shouldBe score
        }
    }
})

class DeepShadesTest : StringSpec({
    "#solve should return proper score pairs of 5 + 6" {
        forall(
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 0), 0),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 1), 2),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 2), 4),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 3), 6),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 4), 8),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 5), 10),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 6), 12),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 7), 14),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 8), 16),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 9), 18),
                row(WindowFactory.withPairs(Face.FIVE, Face.SIX, 10), 20)
        ) { window, score ->
            DeepShades().solve(window) shouldBe score
        }
    }
})

class ColorVarietyTest : StringSpec({
    "#solve should return proper score for complete sets of colors" {
        forall(
                row(WindowFactory.withColorSets(0), 0),
                row(WindowFactory.withColorSets(1), 4),
                row(WindowFactory.withColorSets(2), 8),
                row(WindowFactory.withColorSets(3), 12)
        ) { window, score ->
            ColorVariety().solve(window) shouldBe score
        }
    }
})

class ShadeVarietyTest : StringSpec({
    "#solve should return proper score for complete sets of values" {
        forall(
                row(WindowFactory.withValueSets(0), 0),
                row(WindowFactory.withValueSets(1), 5),
                row(WindowFactory.withValueSets(2), 10)
        ) { window, score ->
            ShadeVariety().solve(window) shouldBe score
        }
    }
})

class ColorDiagonalsTest : StringSpec({
    "#solve should return 20 when every diagonal neighbor is same color" {
        val dice = listOf(
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE)),
                listOf(Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE)),
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE)),
                listOf(Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE))
        )
        val window = Window(dice)

        ColorDiagonals().solve(window) shouldBe 20
    }

    "#solve should return 0 when no diagonal neighbors are same color" {
        val dice = listOf(
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE)),
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE)),
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE)),
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE))
        )
        val window = Window(dice)

        ColorDiagonals().solve(window) shouldBe 0
    }

    "#solve should return 2 when exactly one set of diagonal neighbors are same color" {
        val dice = listOf(
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.YELLOW, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE)),
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE)),
                listOf(Die(Color.YELLOW, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.YELLOW, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE)),
                listOf(Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE), Die(Color.RED, Face.ONE), Die(Color.BLUE, Face.ONE))
        )
        val window = Window(dice)

        ColorDiagonals().solve(window) shouldBe 2
    }
})