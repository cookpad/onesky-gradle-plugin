package rejasupotaro.onesky.plugin

import com.github.kittinunf.result.Result
import org.gradle.api.tasks.TaskAction
import java.io.File

open class DownloadTranslationTask : OneskyTask() {
    val resDir by lazy { File("${project.projectDir.absolutePath}/src/main/res") }

    val locales by lazy {
        if (oneskyExtension.locales.isNotEmpty()) {
            oneskyExtension.locales.split(" ")
        } else {
            resDir
                    .listFiles()
                    .filter { it.name.startsWith("values-") }
                    .map { it.name.replace("values-", "") }
                    .map { it.replace("in", "id") }
                    .map { it.replace("-r", "-") }
        }
    }

    init {
        group = "Translation"
        description = "Download specified translation files (values-*/strings.xml)"
    }

    @TaskAction
    fun downloadTranslation() {
        if (locales.isEmpty()) {
            throw IllegalArgumentException("You should provide locales or values-* directories should be created before running this task")
        }

        locales.forEach { locale ->
            val (request, response, result) = oneskyClient.download(locale)
            when (result) {
                is Result.Success -> {
                    val file = File("${resDir.absolutePath}/values-${locale.replace("id", "in").replace("-", "-r")}/strings.xml")
                    file.writeText(result.value)
                }
                is Result.Failure -> {
                    throw result.error
                }
            }
        }
    }
}

