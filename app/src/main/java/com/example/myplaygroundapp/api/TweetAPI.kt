package com.example.myplaygroundapp.api

import com.example.myplaygroundapp.model.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetAPI {
    @GET("v3/b/64ce7cba9d312622a38c6bf2?meta=false")
    suspend  fun getTweet(@Header("X-JSON-Path") category: String): Response<List<Tweet>>

    @GET("v3/b/64ce7cba9d312622a38c6bf2?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun  getCategories(): Response<List<String>>
}