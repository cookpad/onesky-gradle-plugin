package com.cookpad.android.onesky.plugin.tasks

import com.cookpad.android.onesky.plugin.localeFromValuesDirName
import com.cookpad.android.onesky.plugin.valuesDirNameFromLocale
import com.github.kittinunf.result.Result
import org.gradle.api.tasks.TaskAction
import java.io.File

open class DownloadTranslationTask : OneskyTask() {
    init {
        group = "Translation"
        description = "Download specified translation files (values-*/strings.xml)"
    }

    private val resDir by lazy { File("${project.projectDir.absolutePath}/src/main/res") }

    private val locales by lazy {
        if (oneskyExtension!!.locales.isEmpty()) {
            resDir.listFiles()
                    .filter { it.name.startsWith("values-") }
                    .filter {
                        File("${it.absolutePath}/strings.xml").exists()
                    }
                    .map { localeFromValuesDirName(it.name) }
        } else {
            oneskyExtension!!.locales
        }
    }

    @TaskAction
    fun downloadTranslation() {
        if (locales.isEmpty()) {
            throw IllegalArgumentException("You should provide locales or values-* directories should be created before running this task")
        }

        locales.forEach { locale ->
            val file = targetStringsFile(locale)
            print("Downloading $locale translation into ${file.absolutePath} ... ")

            val result = oneskyClient.download(locale)
            when (result) {
                is Result.Success -> {
                    file.writeText(result.value)
                    println("Done!")
                }
                is Result.Failure -> {
                    println("Failed!")
                    throw result.error
                }
            }
        }
    }

    private fun targetStringsFile(locale: String): File {
        val valuesDir = File("${resDir.absolutePath}/${valuesDirNameFromLocale(locale)}")
        if (!valuesDir.exists()) {
            valuesDir.mkdir()
        }
        val stringsFile = File("${valuesDir.absolutePath}/strings.xml")
        if (!stringsFile.exists()) {
            stringsFile.createNewFile()
        }
        return stringsFile
    }
}
