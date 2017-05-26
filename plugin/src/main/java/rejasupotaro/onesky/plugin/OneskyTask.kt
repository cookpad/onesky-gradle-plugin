package rejasupotaro.onesky.plugin

import org.gradle.api.DefaultTask

open class OneskyTask : DefaultTask() {
    val oneskyExtension by lazy {
        project.extensions.findByType(OneskyExtension::class.java)
    }

    val oneskyClient by lazy {
        val apiKey = oneskyExtension.apiKey
        val apiSecret = oneskyExtension.apiSecret
        val projectId = oneskyExtension.projectId
        OneskyClient(apiKey, apiSecret, projectId)
    }
}

