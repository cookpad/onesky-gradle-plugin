package rejasupotaro.onesky.plugin

open class OneskyExtension {
    var apiKey: String = ""
    var apiSecret: String = ""
    var projectId: Int = 0
    var locales: Set<String> = setOf()

    var excludes: Set<String> = setOf()
    var includes: Set<String> = setOf()

    fun locales(locales: Array<String>) {
        this.locales = locales.toSet()
    }

    fun excludes(excludes: Array<String>){
        this.excludes = excludes.toSet()
    }

    fun includes(includes: Array<String>){
        this.includes = includes.toSet()
    }

    val filtersIndex: Int
    get(){
        return when {
            includes.isEmpty() && excludes.isEmpty() -> 0
            excludes.isEmpty() && !includes.isEmpty() -> 1
            includes.isEmpty() && !excludes.isEmpty() -> 2
            else -> 3
        }
    }
}

