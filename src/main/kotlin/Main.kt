fun main(args: Array<String>) {
    val filePath = "/Users/nate-piche/dice-test.jpg"
    val azurePrediction = AzureClient.getPredictions(filePath)
    val diceColor = DiceClient.getDice(filePath)
    val window = azurePrediction.toWindow(diceColor)
    window.display()
}
