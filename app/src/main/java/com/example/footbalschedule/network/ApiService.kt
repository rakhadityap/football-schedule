package com.example.footbalschedule.network


import com.example.footbalschedule.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService
{
    @GET("search_all_leagues.php?s=Soccer")
    fun getLeagueList(): Call<LeagueResponse>

    @GET("lookupleague.php")
    fun getLeagueDetail(@Query("id") id: String?): Call<LeagueDetailResponse>

    @GET("eventsnextleague.php")
    fun getSchedule(@Query("id") id: String?): Call<MatchResponse>

    @GET("eventspastleague.php")
    fun getPastEvents(@Query("id") id: String?): Call<MatchResponse>

    @GET("lookuptable.php")
    fun getStandings(@Query("l") l: String?): Call<StandingsResponse>

    @GET("lookupevent.php")
    fun getEventDetails(@Query("id") eventId: String?): Call<EventDetailResponse>

    @GET("lookup_all_teams.php")
    fun getTeamList(@Query("id") id: String?): Call<TeamResponse>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") clubId: String?): Call<TeamResponse>

    @GET("lookup_all_players.php")
    fun getPlayerList(@Query("id") id: String): Call<PlayerListResponse>


}