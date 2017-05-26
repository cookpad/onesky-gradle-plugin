package rejasupotaro.onesky.plugin

import org.gradle.api.tasks.TaskAction
import java.io.File

open class UploadTranslationTask : OneskyTask() {
    init {
        group = ""
        description = ""
    }

    @TaskAction
    fun uploadTranlation() {
        val defaultTranslationFile = File("${project.projectDir.absolutePath}/src/main/res/values/strings.xml")
        val (request, response, result) = oneskyClient.upload(defaultTranslationFile)
    }
}

