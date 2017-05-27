package rejasupotaro.onesky.plugin

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GsonProviderTest {
    @Test
    fun testDateTimeConverter() {
        val translation= gson.fromJson(
                "{\"last_updated_at\": \"2017-05-23T12:23:31+0000\"}",
                ShowTranslationProgressTask.Translation::class.java)
        assertThat(translation.lastUpdatedAt!!.year).isEqualTo(2017)
        assertThat(translation.lastUpdatedAt!!.monthOfYear).isEqualTo(5)
        assertThat(translation.lastUpdatedAt!!.dayOfMonth).isEqualTo(23)
    }
}

