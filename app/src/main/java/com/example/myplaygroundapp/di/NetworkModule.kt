package com.example.myplaygroundapp.di

import com.example.myplaygroundapp.api.TweetAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    }

    @Singleton
    @Provides
    fun providesTweetApi(retrofit: Retrofit): TweetAPI {
        return retrofit.create(TweetAPI::class.java)
    }

}