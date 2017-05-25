package rejasupotaro.onesky.plugin

import org.gradle.api.DefaultTask

open class OneskyTask : DefaultTask() {
    val oneskyClient by lazy {
        val extension = project.extensions.findByType(OneskyExtension::class.java)
        val apiKey = extension.apiKey
        val apiSecret = extension.apiSecret
        val projectId = extension.projectId
        OneskyClient(apiKey, apiSecret, projectId)
    }
}

