package rejasupotaro.onesky.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class OneskyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            extensions.create("onesky", OneskyExtension::class.java)
            tasks.create("uploadTranslation", UploadTranslationTask::class.java)
            tasks.create("downloadTranslation", DownloadTranslationTask::class.java)
        }
    }
}


