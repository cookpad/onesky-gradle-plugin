package rejasupotaro.onesky.plugin.tasks

import org.gradle.api.DefaultTask
import rejasupotaro.onesky.plugin.OneskyExtension
import rejasupotaro.onesky.plugin.client.Onesky
import java.io.File
import java.util.regex.Pattern

open class OneskyTask : DefaultTask() {
    val version by lazy {
        "0.2.0"
    }

    val oneskyExtension by lazy {
        project.extensions.findByType(OneskyExtension::class.java)
    }

    val oneskyClient by lazy {
        val apiKey = oneskyExtension.apiKey
        val apiSecret = oneskyExtension.apiSecret
        val projectId = oneskyExtension.projectId

        Onesky(apiKey, apiSecret, projectId)
    }
    
    val resDir by lazy { File("${project.projectDir.absolutePath}/src/main/res") }

    val valuesDir by lazy {
        File(resDir, "values")
    }

    val excludes by lazy {
        oneskyExtension.excludes
                .map { Pattern.compile(it) }
    }

    val includes by lazy {
        oneskyExtension.includes
                .map { Pattern.compile(it) }
    }

    val filteredFiles by lazy {
        when (oneskyExtension.filtersIndex) {
            0 -> valuesDir.listFiles().asList()

            1 -> valuesDir.listFiles()
                    .filter { includes.any { p -> p.matcher(it.name).matches() } }

            2 -> valuesDir.listFiles()
                    .filter { excludes.all { p -> !p.matcher(it.name).matches() } }

            else -> valuesDir.listFiles()
                    .filter { includes.any { p -> p.matcher(it.name).matches() } }
                    .filter { excludes.all { p -> !p.matcher(it.name).matches() } }
        }
    }
}

