import factories.WindowFactory
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ColumnShadeVarietyTest : StringSpec({
    "#solve should return 4 for one match" {
        val window = WindowFactory.withUniqueValueColumns(1)
        ColumnShadeVariety().solve(window) shouldBe 4
    }

    "#solve should return 8 for two matches" {
        val window = WindowFactory.withUniqueValueColumns(2)
        ColumnShadeVariety().solve(window) shouldBe 8
    }

    "#solve should return 0 for no matches" {
        val window = WindowFactory.withUniqueValueColumns(0)
        ColumnShadeVariety().solve(window) shouldBe 0
    }
})

class RowShadeVarietyTest : StringSpec({
    "#solve should return 5 for one match" {
        val window = WindowFactory.withUniqueValueRows(1)
        RowShadeVariety().solve(window) shouldBe 5
    }

    "#solve should return 10 for two matches" {
        val window = WindowFactory.withUniqueValueRows(2)
        RowShadeVariety().solve(window) shouldBe 10
    }

    "#solve should return 0 no matches" {
        val window = WindowFactory.withUniqueValueRows(0)
        RowShadeVariety().solve(window) shouldBe 0
    }
})

class LightShadesTest : StringSpec({
    "#solve should return 2 for one pair of 1 + 2" {
        val window = WindowFactory.withPairs(Face.ONE, Face.TWO, 1)
        LightShades().solve(window) shouldBe 2
    }
})

class MediumShadesTest : StringSpec({
    "#solve should return 2 for one pair of 3 + 4" {
        val window = WindowFactory.withPairs(Face.THREE, Face.FOUR, 1)
        MediumShades().solve(window) shouldBe 2
    }
})

class DeepShadesTest : StringSpec({
    "#solve should return 2 for one pair of 5 + 6" {
        val window = WindowFactory.withPairs(Face.FIVE, Face.SIX, 1)
        DeepShades().solve(window) shouldBe 2
    }
})
