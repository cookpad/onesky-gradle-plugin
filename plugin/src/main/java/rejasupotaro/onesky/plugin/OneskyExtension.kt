package rejasupotaro.onesky.plugin

open class OneskyExtension {
    var apiKey: String = ""
    var apiSecret: String = ""
    var projectId: Int = 0
    var locales: Set<String> = setOf()

    fun locales(locales: Array<String>) {
        this.locales = locales.toSet()
    }
}

