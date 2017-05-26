package rejasupotaro.onesky.plugin

import com.github.kittinunf.result.Result
import org.gradle.api.tasks.TaskAction
import java.io.File

open class UploadTranslationTask : OneskyTask() {
    init {
        group = "Translation"
        description = "Upload the default translation file (values/strings.xml)"
    }

    @TaskAction
    fun uploadTranlation() {
        val file = File("${project.projectDir.absolutePath}/src/main/res/values/strings.xml")
        print("Uploading ${file.absolutePath} ... ")
        val result = oneskyClient.upload(file)
        when (result) {
            is Result.Success -> {
                println("Done!")
            }
            is Result.Failure -> {
                println("Failed!")
                throw result.error
            }
        }
    }
}

