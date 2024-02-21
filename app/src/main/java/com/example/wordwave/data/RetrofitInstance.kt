package com.example.wordwave.data

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TokenInterceptor : Interceptor {

    override fun intercept(chain: Chain): Response {

        //rewrite the request to add bearer token
        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Token token=\"718e64c032ba543fa084f884ac5e4179\"")
            .build()
        return chain.proceed(newRequest)
    }

}

object RetrofitInstance {
    private const val BASE_URL = "https://favqs.com/"
    val api: ApiService by lazy {
        val interceptor = TokenInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}