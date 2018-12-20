import okhttp3.*
import java.io.File

object AzureClient {
    private val azureUrl: String = System.getenv("AZURE_URL")!!
    private val cli: OkHttpClient = OkHttpClient()
    private val mediaType: MediaType = MediaType.parse("multipart/form-data")!!

    fun getPredictions(filePath: String): AzurePrediction {
        val reqBody = makeBody(filePath)
        val req = makeRequest(reqBody)
        val res = cli.newCall(req).execute()

        return AzurePredictionBuilder.build(res.body()!!.string())!!
    }

    private fun makeBody(filePath: String): MultipartBody =
        with(MultipartBody.Builder()) {
            setType(MultipartBody.FORM)
            addFormDataPart("image", "gameBoard", RequestBody.create(mediaType, File(filePath)))
            build()
        }

    private fun makeRequest(body: RequestBody): Request =
            with(Request.Builder()) {
                url(azureUrl)
                post(body)
                addHeader("Prediction-Key", "d13d25dc3d7543bfaed95bc39811db4e")
                addHeader("Content-Type", "application/octet-stream")
                build()
            }
}