import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.File
import java.util.concurrent.TimeUnit

object DiceClient {
    private const val timeout: Long = 60000
    private val cli: OkHttpClient = with(OkHttpClient.Builder()) {
        readTimeout(timeout, TimeUnit.MILLISECONDS)
        build()
    }

    private val mediaType: MediaType = MediaType.parse("multipart/form-data")!!

    fun getDice(filePath: String): Dice {
        val reqBody = makeBody(filePath)
        val req = makeRequest(reqBody)
        val res = cli.newCall(req).execute()

        return DiceBuilder.build(res.body()!!.string())!!
    }

    private fun makeBody(filePath: String): MultipartBody =
            with(MultipartBody.Builder()) {
                setType(MultipartBody.FORM)
                addFormDataPart("image", "gameBoard", RequestBody.create(mediaType, File(filePath)))
                build()
            }

    private fun makeRequest(body: RequestBody): Request =
            with(Request.Builder()) {
                url(BaseUrls.diceUrl)
                post(body)
                addHeader("Content-Type", "multipart/form-data")
                build()
            }
}