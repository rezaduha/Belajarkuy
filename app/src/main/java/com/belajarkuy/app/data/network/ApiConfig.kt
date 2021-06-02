package com.belajarkuy.app.data.network

import android.content.Context
import com.belajarkuy.app.utils.Preference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    const val BASE_URL = "https://mocki.io/v1/"
    const val NEWS_URL = "https://newsapi.org/v2/"

    fun getApiService(context: Context): ApiService {
        val token = Preference.getToken(context)
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("token", token.toString()).build()
                chain.proceed(request)
            }
            .addNetworkInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun getNewsApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NEWS_URL)
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}