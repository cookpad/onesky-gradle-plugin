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
                    .map { localeFromResDirName(it.name) }
        }
    }

    @TaskAction
    fun downloadTranslation() {
        if (locales.isEmpty()) {
            throw IllegalArgumentException("You should provide locales or values-* directories should be created before running this task")
        }

        locales.forEach { locale ->
            val targetResDirAndFileName = "${resDirNameFromLocale(locale)}/strings.xml"
            print("Downloading $targetResDirAndFileName ... ")

            val (_, _, result) = oneskyClient.download(locale)
            when (result) {
                is Result.Success -> {
                    val file = File("${resDir.absolutePath}/$targetResDirAndFileName")
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

    private fun resDirNameFromLocale(locale: String): String {
        return "values-${locale.replace("id", "in").replace("-", "-r")}"
    }

    private fun localeFromResDirName(dirName: String): String {
        return dirName.replace("values-", "").replace("in", "id").replace("-r", "'-")
    }
}
