package com.example.footbalschedule.app.network

import com.example.footbalschedule.app.Const.BASE_URL
import com.example.footbalschedule.app.Const.REQUEST_TIMEOUT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient
{
    val retrofit by lazy{
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(initOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun initOkHttp() : OkHttpClient
    {
        return OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
    }
}