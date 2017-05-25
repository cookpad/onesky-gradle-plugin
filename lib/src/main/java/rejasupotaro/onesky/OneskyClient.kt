package rejasupotaro.onesky

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpUpload
import com.github.kittinunf.result.Result
import org.apache.commons.codec.binary.Hex
import java.io.File
import java.security.MessageDigest

class OneskyClient(val apiKey: String, val apiSecret: String, val projectId: Int) {

    private val endpoint = "https://platform.api.onesky.io"

    private val version = 1

    private val urlPrefix
        get() = "$endpoint/$version"

    fun upload(path: String, handler: (Result<String, FuelError>) -> Unit) {
        val params = authParams()
        params.add("file_format" to "ANDROID_XML")

        "$urlPrefix/projects/$projectId/files".httpUpload(parameters = params)
                .source { _, _ -> File(path) }
                .name { "file" }
                .progress { _, _ -> }
                .responseString { _, _, result ->
                    handler.invoke(result)
                }
    }

    fun download() {
    }

    fun authParams(): MutableList<Pair<String, String>> {
        val md = MessageDigest.getInstance("MD5")
        val timestamp = (System.currentTimeMillis() / 1000L).toString()
        val devHash = Hex.encodeHexString(md.digest((timestamp + apiSecret).toByteArray()))

        return mutableListOf("api_key" to apiKey,
                "dev_hash" to devHash,
                "timestamp" to timestamp)
    }
}


