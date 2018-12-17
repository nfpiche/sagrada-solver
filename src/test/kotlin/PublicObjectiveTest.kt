import factories.WindowFactory
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class PublicObjectiveTest : StringSpec({
    "RowShadeVariety solve should return 5 for one match" {
        val window = WindowFactory.withUniqueValueRows(1)
        RowShadeVariety().solve(window) shouldBe 5
    }

    "RowShadeVariety solve should return 10 for two matches" {
        val window = WindowFactory.withUniqueValueRows(2)
        RowShadeVariety().solve(window) shouldBe 10
    }

    "RowShadeVariety solve should return 0 no matches" {
        val window = WindowFactory.withUniqueValueRows(0)
        RowShadeVariety().solve(window) shouldBe 0
    }

    "ColumnShadeVariety solve should return 4 for one match" {
        val window = WindowFactory.withUniqueValueColumns(1)
        ColumnShadeVariety().solve(window) shouldBe 4
    }

    "ColumnShadeVariety solve should return 8 for two matches" {
        val window = WindowFactory.withUniqueValueColumns(2)
        ColumnShadeVariety().solve(window) shouldBe 8
    }

    "ColumnShadeVariety solve should return 0 for no matches" {
        val window = WindowFactory.withUniqueValueColumns(0)
        ColumnShadeVariety().solve(window) shouldBe 0
    }
})
