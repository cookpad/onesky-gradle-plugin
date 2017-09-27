package rejasupotaro.onesky.plugin.client

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import java.io.File

open class HttpClient {

    open fun get(url: String, params: List<Pair<String, String>>): Result<String, FuelError> {
        val (r, o, result) = Fuel.get(url, parameters = params)
                .responseString()

        if(result is Result.Failure) {
            println("Failed! ${result.error.exception.javaClass.simpleName}")
//            println(r.toString())
//            println(o.toString())
        }

        return result
    }

    open fun post(url: String, params: List<Pair<String, String>>, file: File): Result<String, FuelError> {
        val (_, _, result) = Fuel.upload(url, parameters = params)
                .source { _, _ -> file }
                .name { "file" }
                .responseString()


        return result
    }
}
