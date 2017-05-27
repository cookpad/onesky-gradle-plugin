package rejasupotaro.onesky.plugin.tasks

import org.gradle.api.DefaultTask
import rejasupotaro.onesky.plugin.OneskyExtension
import rejasupotaro.onesky.plugin.client.Onesky

open class OneskyTask : DefaultTask() {
    val oneskyExtension by lazy {
        project.extensions.findByType(OneskyExtension::class.java)
    }

    val oneskyClient by lazy {
        val apiKey = oneskyExtension.apiKey
        val apiSecret = oneskyExtension.apiSecret
        val projectId = oneskyExtension.projectId
        Onesky(apiKey, apiSecret, projectId)
    }
}

