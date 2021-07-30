package com.cookpad.android.onesky.plugin.tasks

import com.cookpad.android.onesky.plugin.OneskyExtension
import com.cookpad.android.onesky.plugin.client.Onesky
import com.cookpad.android.onesky.plugin.gson
import com.github.kittinunf.result.Result
import com.google.gson.annotations.SerializedName
import com.jakewharton.fliptables.FlipTable
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


open class ShowTranslationProgressTask : DefaultTask() {
    private val oneskyExtension by lazy {
        project.extensions.findByType(OneskyExtension::class.java)
    }

    private val oneskyClient by lazy {
        val apiKey = oneskyExtension!!.apiKey
        val apiSecret = oneskyExtension!!.apiSecret
        val projectId = oneskyExtension!!.projectId
        Onesky(apiKey, apiSecret, projectId)
    }

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
        val headers = arrayOf("Locale", "Progress", "Last update at")
        val data = translations.map {
            val lastUpdatedAt = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm").print(it.lastUpdatedAt)
            arrayOf(it.code, it.progress, lastUpdatedAt)
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
