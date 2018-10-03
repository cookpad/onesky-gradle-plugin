package com.cookpad.android.onesky.plugin

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FilesTest {
    @Test
    fun testLocalFromValuesDirName() {
        listOf(
                "values-ar" to "ar",
                "values-es" to "es",
                "values-in" to "id",
                "values-pt-rBR" to "pt-BR",
                "values-zh-rTW" to "zh-TW"
        ).forEach {
            val actual = localeFromValuesDirName(it.first)
            assertThat(actual).isEqualTo(it.second)
        }
    }

    @Test
    fun testValuesDirName() {
        listOf(
                "ar" to "values-ar",
                "es" to "values-es",
                "id" to "values-in",
                "pt-BR" to "values-pt-rBR",
                "zh-TW" to "values-zh-rTW"
        ).forEach {
            val actual = valuesDirNameFromLocale(it.first)
            assertThat(actual).isEqualTo(it.second)
        }
    }
}

