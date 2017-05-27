package rejasupotaro.onesky.plugin

import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.gradle.api.tasks.TaskAction
import com.jakewharton.fliptables.FlipTable
import org.joda.time.DateTime


open class ShowTranslationProgressTask : OneskyTask() {
    init {
        group = "Translation"
        description = "Show translation progress"
    }

    @TaskAction
    fun showTranslationProgress() {
        val result = oneskyClient.languages()
        when (result) {
            is Result.Success -> {
                val translations = jsonToTranslations(result.value)
                printProgress(translations)
            }
            is Result.Failure -> {
                println("Failed!")
                throw result.error
            }
        }
    }

    private fun jsonToTranslations(json: String): List<Translation> {
        val response = gson.fromJson(json, Response::class.java)
        return response.data.filterNot {
            it.isBaseLanguage
        }.filter {
            it.lastUpdatedAt != null
        }.sortedWith(compareBy(
                { -it.progress.replace("%", "").toFloat() },
                { it.code })
        )
    }

    private fun printProgress(translations: List<Translation>) {
        val headers = arrayOf("Locale", "Progress")
        val data = translations.map {
            arrayOf(it.code, it.progress)
        }.toTypedArray()
        println(FlipTable.of(headers, data))
    }

    class Response {
        @SerializedName("data") val data = listOf<Translation>()
    }

    class Translation {
        @SerializedName("code") val code = ""
        @SerializedName("locale") val locale = ""
        @SerializedName("region") val region = ""
        @SerializedName("is_base_language") val isBaseLanguage = false
        @SerializedName("translation_progress") val progress = ""
        @SerializedName("last_updated_at") val lastUpdatedAt: DateTime? = null
        @SerializedName("last_updated_at_timestamp") val lastUpdatedAtTimestamp: String? = ""
    }
}
