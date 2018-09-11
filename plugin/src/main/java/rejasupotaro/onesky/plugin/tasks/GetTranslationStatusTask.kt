package rejasupotaro.onesky.plugin.tasks

import com.github.kittinunf.result.Result
import com.google.gson.annotations.SerializedName
import org.gradle.api.tasks.TaskAction
import rejasupotaro.onesky.plugin.gson

open class GetTranslationStatusTask : OneskyTask() {
    init {
        group = "Translation"
        description = "Get translation status"
    }

    @TaskAction
    fun getTranslationStatus() {
        // TODO: change to lead configuration files
        val result = oneskyClient.getTranslationsStatus("en-US")
        when (result) {
            is Result.Success -> {
                val translations = jsonToTranslationStatus(result.value)
                println(translations)
            }
            is Result.Failure -> {
                println("Failed!")
                throw result.error
            }
        }

    }

    private fun jsonToTranslationStatus(json: String): List<TranslationStatus> {
        return gson.fromJson(json, Response::class.java).data
    }

    class Response {
        @SerializedName("data") val data = listOf<TranslationStatus>()
    }

    class TranslationStatus {
        @SerializedName("file_name") val fileName = ""
        @SerializedName("locale") val locale = listOf<Locale>()
        @SerializedName("progress") val progress = ""
        @SerializedName("string_count") val stringCount = 0
        @SerializedName("word_count") val wordCount = 0
    }

    class Locale {
        @SerializedName("code") val code = ""
        @SerializedName("english_name") val englishName = ""
        @SerializedName("local_name") val localName = ""
        @SerializedName("locale") val locale = ""
        @SerializedName("region") val region = ""
    }
}
