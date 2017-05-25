package rejasupotaro.onesky.plugin

import com.github.kittinunf.result.Result
import org.gradle.api.tasks.TaskAction
import java.io.File

open class DownloadTranslationTask : OneskyTask() {
    init {
        group = ""
        description = ""
    }

    @TaskAction
    fun downloadTranslation() {
        val resDir = File("${project.projectDir.absolutePath}/src/main/res")
        val locales = resDir
                .listFiles()
                .filter { it.name.startsWith("values-") }
                .map { it.name.replace("values-", "") }
                .map { it.replace("in", "id") }
                .map { it.replace("-r", "-") }

        locales.forEach { locale ->
            println(locale)
            oneskyClient.download(locale) { result ->
                when (result) {
                    is Result.Success -> {
                        val file = File("${resDir.absolutePath}/values-${locale.replace("id", "in").replace("-", "-r")}/strings.xml")
                        file.writeText(result.value)
                    }
                    is Result.Failure -> {
                        println("error")
                        throw result.error
                    }
                }
            }
        }
    }
}

