package rejasupotaro.onesky.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import rejasupotaro.onesky.plugin.tasks.DownloadTranslationTask
import rejasupotaro.onesky.plugin.tasks.ShowTranslationProgressTask
import rejasupotaro.onesky.plugin.tasks.UploadTranslationTask

class OneskyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            extensions.create("onesky", OneskyExtension::class.java)

            tasks.create("downloadTranslation", DownloadTranslationTask::class.java)
            tasks.create("uploadTranslation", UploadTranslationTask::class.java)
            tasks.create("showTranslationProgress", ShowTranslationProgressTask::class.java)
        }
    }
}


