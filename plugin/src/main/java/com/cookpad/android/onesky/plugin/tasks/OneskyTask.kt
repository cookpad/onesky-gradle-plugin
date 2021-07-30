package com.cookpad.android.onesky.plugin.tasks

import com.cookpad.android.onesky.plugin.OneskyExtension
import com.cookpad.android.onesky.plugin.client.Onesky
import org.gradle.api.DefaultTask

open class OneskyTask : DefaultTask() {
    internal val oneskyExtension by lazy {
        project.extensions.findByType(OneskyExtension::class.java)
    }

    internal val oneskyClient by lazy {
        val apiKey = oneskyExtension!!.apiKey
        val apiSecret = oneskyExtension!!.apiSecret
        val projectId = oneskyExtension!!.projectId
        Onesky(apiKey, apiSecret, projectId)
    }
}

