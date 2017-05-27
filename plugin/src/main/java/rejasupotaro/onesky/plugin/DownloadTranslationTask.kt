package rejasupotaro.onesky.plugin

import com.github.kittinunf.result.Result
import org.gradle.api.tasks.TaskAction
import java.io.File

open class DownloadTranslationTask : OneskyTask() {
    init {
        group = "Translation"
        description = "Download specified translation files (values-*/strings.xml)"
    }

    val resDir by lazy { File("${project.projectDir.absolutePath}/src/main/res") }

    val locales by lazy {
        if (oneskyExtension.locales.isNotEmpty()) {
            oneskyExtension.locales.split(" ")
        } else {
            resDir
                    .listFiles()
                    .filter { it.name.startsWith("values-") }
                    .map { localeFromValuesDirName(it.name) }
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
