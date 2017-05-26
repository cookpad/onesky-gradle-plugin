package rejasupotaro.onesky.plugin

import org.gradle.api.tasks.TaskAction
import java.io.File

open class UploadTranslationTask : OneskyTask() {
    init {
        group = "Translation"
        description = "Upload the default translation file (values/strings.xml)"
    }

    @TaskAction
    fun uploadTranlation() {
        val defaultTranslationFile = File("${project.projectDir.absolutePath}/src/main/res/values/strings.xml")
        val (request, response, result) = oneskyClient.upload(defaultTranslationFile)
    }
}

