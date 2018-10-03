package com.cookpad.android.onesky.plugin.tasks

import org.gradle.api.DefaultTask
import com.cookpad.android.onesky.plugin.OneskyExtension
import com.cookpad.android.onesky.plugin.client.Onesky

open class OneskyTask : DefaultTask() {
    val oneskyExtension by lazy {
        project.extensions.findByType(OneskyExtension::class.java)
    }

    val oneskyClient by lazy {
        val apiKey = oneskyExtension!!.apiKey
        val apiSecret = oneskyExtension!!.apiSecret
        val projectId = oneskyExtension!!.projectId
        Onesky(apiKey, apiSecret, projectId)
    }
}

