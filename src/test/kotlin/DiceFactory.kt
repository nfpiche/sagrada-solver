object DiceFactory {
    fun makeUniqueValueRow(): List<Die> {
        val values = mutableSetOf<Face>()

        while (values.size < 5) {
            values.add(getRandomFace())
        }

        return values.toList().fold(mutableListOf()) {acc, face ->
            val color = getRandomColor()
            acc.add(Die(color, face))
            acc
        }
    }

    fun makeUniqueColorRow(): List<Die> {
        val values = mutableSetOf<Color>()

        while (values.size < 5) {
            values.add(getRandomColor())
        }

        return values.toList().fold(mutableListOf()) {acc, color ->
            val face = getRandomFace()
            acc.add(Die(color, face))
            acc
        }
    }

    fun makeRepeatedValueRow(): List<Die> {
        val face = getRandomFace()
        return List(5) { Die(getRandomColor(), face)}
    }

    fun makeRepeatedColorRow(): List<Die> {
        val color = getRandomColor()
        return List(5) { Die(color, getRandomFace())}
    }

    fun getRandomColor(): Color = Color.values().toList().shuffled().first()
    fun getRandomFace(): Face = Face.values().toList().shuffled().first()
}