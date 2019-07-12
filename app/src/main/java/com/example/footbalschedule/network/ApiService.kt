package com.example.footbalschedule.network


import com.example.footbalschedule.model.ClubResponse
import com.example.footbalschedule.model.EventDetailResponse
import com.example.footbalschedule.model.FootbalScheduleResponse
import com.example.footbalschedule.model.LeagueResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService
{
    @GET("search_all_leagues.php?s=Soccer")
    fun getLeagueList(): Call<LeagueResponse>

    @GET("eventsnextleague.php")
    fun getSchedule(@Query("id") id: String?): Call<FootbalScheduleResponse>

    @GET("eventspastleague.php")
    fun getPastEvents(@Query("id") id: String?): Call<FootbalScheduleResponse>

    @GET("lookupevent.php")
    fun getEventDetails(@Query("id") eventId: String?): Call<EventDetailResponse>

    @GET("lookupteam.php")
    fun getClub(@Query("id") clubId: String): Call<ClubResponse>


}