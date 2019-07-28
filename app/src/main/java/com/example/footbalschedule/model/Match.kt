package com.example.footbalschedule.model

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("idEvent")
    val scheduleId: String,

    @SerializedName("strDate")
    val scheduleDate: String,

    @SerializedName("strTime")
    val scheduleTime: String,

    @SerializedName("strHomeTeam")
    val homeTeam: String,

    @SerializedName("strAwayTeam")
    val awayTeam: String,

    @SerializedName("intHomeScore")
    val homeScore: String = "0",

    @SerializedName("intAwayScore")
    val awayScore: String = "0",

    @SerializedName("idHomeTeam")
    val idHomeTeam: String,

    @SerializedName("idAwayTeam")
    val idAwayTeam: String
)
{
    companion object
    {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val scheduleId: String = "SCHEDULE_ID"
        const val scheduleDate: String = "SCHEDULE_DATE"
        const val scheduleTime: String = "SCHEDULE_TIME"
        const val homeTeam: String = "HOME_TEAM"
        const val awayTeam: String = "AWAY_TEAM"
        const val homeScore: String = "HOME_SCORE"
        const val awayScore: String = "AWAY_SCORE"
    }
}