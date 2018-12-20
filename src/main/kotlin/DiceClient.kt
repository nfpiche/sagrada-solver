import okhttp3.*
import java.io.File
import java.util.concurrent.TimeUnit

object DiceClient {
    private val diceUrl: String = System.getenv("DICE_URL")!!
    private val cli: OkHttpClient = with(OkHttpClient.Builder()) {
        readTimeout(60000, TimeUnit.MILLISECONDS)
        build()
    }

    private val mediaType: MediaType = MediaType.parse("multipart/form-data")!!

    fun getDice(filePath: String): DiceColor {
        val reqBody = makeBody(filePath)
        val req = makeRequest(reqBody)
        val res = cli.newCall(req).execute()

        return DiceColorBuilder.build(res.body()!!.string())!!
    }

    private fun makeBody(filePath: String): MultipartBody =
            with(MultipartBody.Builder()) {
                setType(MultipartBody.FORM)
                addFormDataPart("image", "gameBoard", RequestBody.create(mediaType, File(filePath)))
                build()
            }

    private fun makeRequest(body: RequestBody): Request =
            with(Request.Builder()) {
                url(diceUrl)
                post(body)
                addHeader("Content-Type", "multipart/form-data")
                build()
            }
}