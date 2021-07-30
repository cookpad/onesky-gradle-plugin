package com.cookpad.android.onesky.plugin.tasks

import com.cookpad.android.onesky.plugin.OneskyExtension
import com.cookpad.android.onesky.plugin.client.Onesky
import com.github.kittinunf.result.Result
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class UploadTranslationTask : DefaultTask() {
    private val oneskyClient by lazy {
        val oneskyExtension= project.extensions.findByType(OneskyExtension::class.java)
        val apiKey = oneskyExtension!!.apiKey
        val apiSecret = oneskyExtension.apiSecret
        val projectId = oneskyExtension.projectId
        Onesky(apiKey, apiSecret, projectId)
    }

    init {
        group = "Translation"
        description = "Upload the default translation file (values/strings.xml)"
    }

    @TaskAction
    fun uploadTranslation() {
        val file = File("${project.projectDir.absolutePath}/src/main/res/values/strings.xml")
        print("Uploading ${file.absolutePath} ... ")
        val result = oneskyClient.upload(file)
        when (result) {
            is Result.Success -> {
                println("Done!")
            }
            is Result.Failure -> {
                println("Failed!")
                throw result.error
            }
        }
    }
}

