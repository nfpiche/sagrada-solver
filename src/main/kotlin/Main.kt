fun main(args: Array<String>) {
    val filePath = "/Users/nate-piche/dice-test.jpg"
    val dice = DiceClient.getDice(filePath)
    val window = Window(dice.forWindow(), Color.BLUE)
    window.display()
}

object BaseUrls {
    val diceUrl: String = System.getenv("DICE_URL")!!
    val cloudinaryUrl: String = System.getenv("CLOUDINARY_URL")!!
}
