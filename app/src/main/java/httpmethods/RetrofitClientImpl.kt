package httpmethods

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitClientImpl : RetrofitClient {
    override fun getRetrofit(client: OkHttpClient): Retrofit {
        var URL_BASE = "http://192.168.1.133:9003"
        //var URL_BASE = "http://109.227.157.182:9003"
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()
    }

    override fun buildClient(client: OkHttpClient.Builder): OkHttpClient {
        return client.build()
    }

    override fun getClientWithHeaders(client: OkHttpClient.Builder?, hashMap: HashMap<String, String>): OkHttpClient.Builder {
        val client = client ?: OkHttpClient.Builder()
        client.addInterceptor(MyInterceptor(hashMap))
        return client
    }

    class MyInterceptor(private val hashMap: HashMap<String, String>): Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
            hashMap.forEach { (key, value) ->
                request.addHeader(key,value)
            }
            return chain.proceed(request.build())
        }
    }
}