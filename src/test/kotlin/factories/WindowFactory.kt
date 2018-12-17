package factories

import Die
import Window

object WindowFactory {
    fun withUniqueValueRows(uniqueNumber: Int): Window {
        assert(uniqueNumber < 5)

        val repeatedNumber = 4 - uniqueNumber
        val uniqueRows = List(uniqueNumber) { DiceFactory.makeUniqueValueRow() }
        val repeatedRows = List(repeatedNumber) { DiceFactory.makeRepeatedValueRow() }

        return Window(uniqueRows + repeatedRows)
    }

    fun withUniqueColorRows(uniqueNumber: Int): Window {
        assert(uniqueNumber < 5)

        val repeatedNumber = 4 - uniqueNumber
        val uniqueRows = List(uniqueNumber) { DiceFactory.makeUniqueColorRow() }
        val repeatedRows = List(repeatedNumber) { DiceFactory.makeRepeatedColorRow() }

        return Window(uniqueRows + repeatedRows)
    }

    fun withUniqueValueColumns(uniqueNumber: Int): Window {
        assert(uniqueNumber < 6)

        val dice = MutableList(4) { mutableListOf<Die>() }

        for (y in 0 until uniqueNumber) {
            val uniqueRow = DiceFactory.makeUniqueValueRow()

            for (x in 0 until 4) {
                val current = dice[x]
                current += uniqueRow[x]
            }
        }

        for (y in 0 until 4) {
            val sameRow = DiceFactory.makeRepeatedValueRow()

            for (x in uniqueNumber until 5) {
                val current = dice[y]
                current += sameRow[x]
            }
        }

        return Window(dice)
    }
}