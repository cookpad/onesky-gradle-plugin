package com.cookpad.android.onesky.plugin.tasks

import com.github.kittinunf.result.Result
import org.gradle.api.tasks.TaskAction
import java.io.File

open class uploadTranslationAndMarkAsDeprecated : OneskyTask() {
    init {
        group = "Translation"
        description = "Upload the default translation file (values/strings.xml) and deprecate old translation"
    }

    @TaskAction
    fun uploadTranslationAndMarkAsDeprecated() {
        val file = File("${project.projectDir.absolutePath}/src/main/res/values/strings.xml")
        print("Uploading ${file.absolutePath} ... ")
        val result = oneskyClient.upload(file, isKeepingAllStrings = false)
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

