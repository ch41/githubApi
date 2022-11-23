package com.example.github.data.remote.api

import com.example.github.data.models.GithubResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{username}/repos")
    suspend fun getRepositoriesByUsername(@Path("username") username: String): GithubResponse
}
fun GithubService(): GithubService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.github.com/")
        .client(okHttpClient)
        .build().create(GithubService::class.java)
}