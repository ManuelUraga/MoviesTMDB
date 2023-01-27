package com.uraga.moviestmdb.di

import com.uraga.moviestmdb.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(requestInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
            addInterceptor(HttpLoggingInterceptor().apply {
                HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor.invoke {
            val token =
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMzQ0ZjA4ZjAxNWU0YTdlYTllYjg2MWQ5NjI2ZjYxNSIsInN1YiI6IjVlOTY1ZDU1MmNlZmMyMDAxNDhkNzZjYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Jgeam_F3K_TDIu0eif5Zz8PePuWEbmGFZmOUts30vtU"
            val newRequest = it.request().newBuilder()
                .addHeader("Authorization", token)
                .build()
            it.proceed(newRequest)
        }
    }
}