package httpmethods

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface RetrofitClient {
    fun getRetrofit(client: OkHttpClient): Retrofit

    fun getClientWithHeaders(client: OkHttpClient.Builder? = null, hashMap: HashMap<String, String>): OkHttpClient.Builder

    fun buildClient(client: OkHttpClient.Builder): OkHttpClient


}