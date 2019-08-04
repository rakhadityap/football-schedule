package com.example.footbalschedule.app

import com.example.footbalschedule.app.network.ApiClient
import com.example.footbalschedule.app.network.ApiService

object Const
{
    const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"
    const val REQUEST_TIMEOUT = 10

    val apiService by lazy { ApiClient.retrofit.create(ApiService::class.java) }

}