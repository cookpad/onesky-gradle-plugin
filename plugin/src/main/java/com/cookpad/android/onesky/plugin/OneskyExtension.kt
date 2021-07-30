package com.cookpad.android.onesky.plugin

import org.gradle.api.tasks.Input

open class OneskyExtension {
    @Input
    var apiKey: String = ""
    @Input
    var apiSecret: String = ""
    @Input
    var projectId: Int = 0
    @Input
    var locales: Set<String> = setOf()

    fun locales(locales: Array<String>) {
        this.locales = locales.toSet()
    }
}

