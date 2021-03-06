package factories

import Die
import Color
import Face
import Window

object WindowFactory {
    fun withUniqueValueRows(uniqueNumber: Int): Window {
        assert(uniqueNumber < 5)

        val repeatedNumber = 4 - uniqueNumber
        val uniqueRows = List(uniqueNumber) { DiceFactory.makeUniqueValueRow() }
        val repeatedRows = List(repeatedNumber) { DiceFactory.makeRepeatedValueRow() }

        return Window(uniqueRows + repeatedRows, DiceFactory.getRandomColor())
    }

    fun withUniqueColorRows(uniqueNumber: Int): Window {
        assert(uniqueNumber < 5)

        val repeatedNumber = 4 - uniqueNumber
        val uniqueRows = List(uniqueNumber) { DiceFactory.makeUniqueColorRow() }
        val repeatedRows = List(repeatedNumber) { DiceFactory.makeRepeatedColorRow() }

        return Window(uniqueRows + repeatedRows, DiceFactory.getRandomColor())
    }

    fun withUniqueValueColumns(uniqueNumber: Int): Window {
        assert(uniqueNumber <= 5)

        val dice = MutableList(4) { mutableListOf<Die>() }

        for (y in 0 until uniqueNumber) {
            val uniqueRow = DiceFactory.makeUniqueValueRow()

            for (x in 0..3) {
                val current = dice[x]
                current += uniqueRow[x]
            }
        }

        for (y in uniqueNumber until 5) {
            val sameRow = DiceFactory.makeRepeatedValueRow()

            for (x in 0..3) {
                val current = dice[x]
                current += sameRow[x]
            }
        }

        return Window(dice, DiceFactory.getRandomColor())
    }

    fun withUniqueColorColumns(uniqueNumber: Int): Window {
        assert(uniqueNumber <= 5)

        val dice = MutableList(4) { mutableListOf<Die>() }

        for (y in 0 until uniqueNumber) {
            val uniqueRow = DiceFactory.makeUniqueColorRow()

            for (x in 0..3) {
                val current = dice[x]
                current += uniqueRow[x]
            }
        }

        for (y in uniqueNumber until 5) {
            val sameRow = DiceFactory.makeRepeatedColorRow()

            for (x in 0..3) {
                val current = dice[x]
                current += sameRow[x]
            }
        }

        return Window(dice, DiceFactory.getRandomColor())
    }

    fun withPairs(faceOne: Face, faceTwo: Face, count: Int): Window {
        assert(count < 11)
        val dice = List(4) { DiceFactory.makeUniqueColorRow() }.map { it.toMutableList() }
        var insertedCount = 0

        for (y in 0..4) {
            for (x in 0..3) {
                dice[x][y] = if (insertedCount == count * 2) {
                    DiceFactory.notOfFaces(faceOne, faceTwo)
                } else {
                    when (insertedCount % 2) {
                        0 -> {
                            insertedCount += 1
                            Die(DiceFactory.getRandomColor(), faceOne)
                        }
                        else -> {
                            insertedCount += 1
                            Die(DiceFactory.getRandomColor(), faceTwo)
                        }
                    }
                }
            }
        }

        return Window(dice, DiceFactory.getRandomColor())
    }

    fun withColorSets(count: Int): Window {
        assert(count < 5)

        val dice = List(4) { DiceFactory.makeUniqueColorRow() }.map { it.toMutableList() }
        val colors = Color.values()
        var insertedCount = 0
        var colorIndex = -1

        for (y in 0..4) {
            for (x in 0..3) {
                dice[x][y] = if (insertedCount == count * 5) {
                    Die(Color.BLUE, DiceFactory.getRandomFace())
                } else {
                    colorIndex += 1
                    insertedCount += 1
                    Die(colors[colorIndex % colors.size], DiceFactory.getRandomFace())
                }
            }
        }

        return Window(dice, Color.YELLOW)
    }

    fun withValueSets(count: Int): Window {
        assert(count < 4)

        val dice = List(4) { DiceFactory.makeUniqueColorRow() }.map { it.toMutableList() }
        val values = Face.values()
        var insertedCount = 0
        var valueIndex = -1

        for (y in 0..4) {
            for (x in 0..3) {
                dice[x][y] = if (insertedCount == count * 6) {
                    Die(DiceFactory.getRandomColor(), Face.ONE)
                } else {
                    valueIndex += 1
                    insertedCount += 1
                    Die(DiceFactory.getRandomColor(), values[valueIndex % values.size])
                }
            }
        }

        return Window(dice, DiceFactory.getRandomColor())
    }
}