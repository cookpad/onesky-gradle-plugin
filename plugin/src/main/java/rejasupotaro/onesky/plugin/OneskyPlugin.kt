package rejasupotaro.onesky.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

class OneskyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            OneskyClient().greet()
            tasks.create("uploadTranslationTask", UploadTranslationTask::class.java)
            tasks.create("downloadTranslationTask", DownloadTranslationTask::class.java)
        }
    }
}

open class UploadTranslationTask : DefaultTask() {
    init {
        group = ""
        description = ""
    }

    @TaskAction
    fun uploadTranlation() {
        OneskyClient().greet()
    }
}

open class DownloadTranslationTask : DefaultTask() {
    init {
        group = ""
        description = ""
    }

    @TaskAction
    fun downloadTranslation() {
        OneskyClient().greet()
    }
}
