import factories.WindowFactory
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class WindowTest : StringSpec({
    val window = WindowFactory.withUniqueValueColumns(1)

    "#rows returns the rows of the window" {
        window.rows().size shouldBe 4
        window.rows().all { it.size == 5 } shouldBe true
    }

    "#columns returns the columns of the window" {
        val columns = window.columns()
        val rows = window.rows()

        columns.size shouldBe 5
        columns.all { it.size == 4 } shouldBe true
        columns[0][0] shouldBe rows[0][0]
        columns[0][1] shouldBe rows[1][0]
        columns[0][2] shouldBe rows[2][0]
        columns[0][3] shouldBe rows[3][0]
    }

    "#groupByValue returns a map of face -> count" {
        val row = listOf(
                Die(Color.BLUE, Face.ONE),
                Die(Color.BLUE, Face.TWO),
                Die(Color.BLUE, Face.THREE),
                Die(Color.BLUE, Face.FOUR),
                Die(Color.BLUE, Face.FIVE)
        )
        val knownWindow = Window(listOf(row, row, row, row))
        val expected = mapOf(
                Face.ONE to 4,
                Face.TWO to 4,
                Face.THREE to 4,
                Face.FOUR to 4,
                Face.FIVE to 4
        )

        knownWindow.groupByValue() shouldBe expected
    }

    "#groupByColor returns a map of color -> count" {
        val row = listOf(
                Die(Color.BLUE, Face.ONE),
                Die(Color.RED, Face.ONE),
                Die(Color.GREEN, Face.TWO),
                Die(Color.PURPLE, Face.FOUR),
                Die(Color.YELLOW, Face.ONE)
        )
        val knownWindow = Window(listOf(row, row, row, row))
        val expected = mapOf(
                Color.YELLOW to 4,
                Color.PURPLE to 4,
                Color.GREEN to 4,
                Color.RED to 4,
                Color.BLUE to 4
        )

        knownWindow.groupByColor() shouldBe expected
    }
})