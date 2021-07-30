package com.cookpad.android.onesky.plugin.tasks

import com.cookpad.android.onesky.plugin.OneskyExtension
import com.cookpad.android.onesky.plugin.client.Onesky
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal

open class OneskyTask : DefaultTask() {
    @delegate:Internal
    internal val oneskyExtension by lazy {
        project.extensions.findByType(OneskyExtension::class.java)
    }

    @delegate:Internal
    internal val oneskyClient by lazy {
        val apiKey = oneskyExtension!!.apiKey
        val apiSecret = oneskyExtension!!.apiSecret
        val projectId = oneskyExtension!!.projectId
        Onesky(apiKey, apiSecret, projectId)
    }
}

