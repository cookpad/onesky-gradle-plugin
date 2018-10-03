package com.cookpad.android.onesky.plugin

internal fun valuesDirNameFromLocale(locale: String): String {
    return "values-${locale.replace("id", "in").replace("-", "-r")}"
}

internal fun localeFromValuesDirName(dirName: String): String {
    return dirName.replace("values-", "").replace("in", "id").replace("-r", "-")
}

