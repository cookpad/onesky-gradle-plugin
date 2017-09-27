package rejasupotaro.onesky.plugin.tasks

import com.github.kittinunf.result.Result
import org.gradle.api.tasks.TaskAction
import rejasupotaro.onesky.plugin.valuesDirNameFromLocale
import java.io.File

open class DownloadTranslationTask : OneskyTask() {
    init {
        group = "Translation"
        description = "Download specified translation files (values-*/strings.xml)"
    }

    val locales by lazy {
        oneskyExtension.locales
    }

    @TaskAction
    fun downloadTranslation() {
        if (locales.isEmpty()) {
            throw IllegalArgumentException("You should provide locales or values-* directories should be created before running this task")
        }

        println("Plugin version: ${version}")

        locales.parallelStream().forEach { locale ->
            filteredFiles.forEach { fileO ->
                val file = targetStringsFile(locale, fileO)
                print("Downloading '$locale' translation into ${file.absolutePath} ... ")

                val start = System.currentTimeMillis()
                val result = oneskyClient.downloadFile(locale, fileO.name)
                when (result) {
                    is Result.Success -> {
                        file.writeText(result.value)

                        // prevent OneSky empty files
                        if (file.readText().isBlank()) file.delete()

                        println("Done! ${System.currentTimeMillis() - start}ms")
                    }

                    is Result.Failure -> {
                        if (file.readText() == "<resources/>") file.delete()
                    }
                }
            }
        }
    }

    private fun targetStringsFile(locale: String, source: File): File {
        val valuesDir = File("${resDir.absolutePath}/${valuesDirNameFromLocale(locale)}")
        if (!valuesDir.exists()) {
            valuesDir.mkdir()
        }

        val stringsFile = File("${valuesDir.absolutePath}/${source.name}")
        if (!stringsFile.exists()) {
            stringsFile.createNewFile()
            stringsFile.writeText("<resources/>")  // make new file a valid resource file
        }

        return stringsFile
    }
}
