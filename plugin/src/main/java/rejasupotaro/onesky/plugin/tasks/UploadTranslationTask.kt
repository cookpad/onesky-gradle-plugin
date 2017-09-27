package rejasupotaro.onesky.plugin.tasks

import com.github.kittinunf.result.Result
import org.gradle.api.tasks.TaskAction

open class UploadTranslationTask : OneskyTask() {
    init {
        group = "Translation"
        description = "Upload the default translation file (values/strings.xml)"
    }

    @TaskAction
    fun uploadTranlation() {
        println("Plugin version: ${version}")

        filteredFiles.parallelStream().forEach { file ->
            print("Uploading ${file.absolutePath} ... ")

            val start = System.currentTimeMillis()

            val result = oneskyClient.upload(file)
            when (result) {
                is Result.Success -> {
                    println("Done! ${System.currentTimeMillis() - start}ms")
                }

                is Result.Failure -> {
                    println("Failed!")
                    throw result.error
                }
            }
        }

    }
}

