package rejasupotaro.onesky.plugin

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.File


class OneskyClientTest {
    val oneskyClient: OneskyClient by lazy {
        val apiKey = "<api-key>"
        val apiSecret = "<api-secret>"
        val projectId = 123456789
        OneskyClient(apiKey, apiSecret, projectId)
    }

    @Test
    fun testUrlPrefix() {
        assertThat(oneskyClient.urlPrefix).isEqualTo("https://platform.api.onesky.io/1")
    }

    @Test
    fun testAuthParams() {
        val params = oneskyClient.authParams()
        assertThat(params.map { it.first }).containsOnly("api_key", "dev_hash", "timestamp")
    }

    @Test
    fun testDownload() {
        val httpClient = mock<HttpClient> {
            on {
                get(any<String>(), any<List<Pair<String, String>>>())
            }.doReturn(mock<Result<String, FuelError>> {})
        }
        oneskyClient.httpClient = httpClient

        oneskyClient.download("es")

        val urlCaptor = argumentCaptor<String>()
        val paramsCaptor = argumentCaptor<List<Pair<String, String>>>()
        verify(httpClient).get(
                urlCaptor.capture(),
                paramsCaptor.capture())
        assertThat(urlCaptor.value).isEqualTo("https://platform.api.onesky.io/1/projects/123456789/translations")
        assertThat(paramsCaptor.value.map { it.first }).containsOnly(
                "api_key",
                "dev_hash",
                "timestamp",
                "source_file_name",
                "locale")
    }

    @Test
    fun testUpload() {
        val file = mock<File>()
        val httpClient = mock<HttpClient> {
            on {
                post(any<String>(), any<List<Pair<String, String>>>(), any<File>())
            }.doReturn(mock<Result<String, FuelError>> {})
        }
        oneskyClient.httpClient = httpClient

        oneskyClient.upload(file)

        val urlCaptor = argumentCaptor<String>()
        val paramsCaptor = argumentCaptor<List<Pair<String, String>>>()
        verify(httpClient).post(
                urlCaptor.capture(),
                paramsCaptor.capture(),
                eq(file))
        assertThat(urlCaptor.value).isEqualTo("https://platform.api.onesky.io/1/projects/123456789/files")
        assertThat(paramsCaptor.value.map { it.first }).containsOnly(
                "api_key",
                "dev_hash",
                "timestamp",
                "file_format")
    }

    @Test
    fun testLanguages() {
        val httpClient = mock<HttpClient> {
            on {
                get(any<String>(), any<List<Pair<String, String>>>())
            }.doReturn(mock<Result<String, FuelError>> {})
        }
        oneskyClient.httpClient = httpClient

        oneskyClient.languages()

        val urlCaptor = argumentCaptor<String>()
        val paramsCaptor = argumentCaptor<List<Pair<String, String>>>()
        verify(httpClient).get(
                urlCaptor.capture(),
                paramsCaptor.capture())
        assertThat(urlCaptor.value).isEqualTo("https://platform.api.onesky.io/1/projects/123456789/languages")
        assertThat(paramsCaptor.value.map { it.first }).containsOnly(
                "api_key",
                "dev_hash",
                "timestamp")
    }
}
