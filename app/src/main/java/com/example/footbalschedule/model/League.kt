package com.example.footbalschedule.model

import com.google.gson.annotations.SerializedName

data class League(
        @SerializedName("idLeague")
        val leagueId: String,

        @SerializedName("strLeague")
        val leagueName: String,

        @SerializedName("strBadge")
        val leagueBadge: String)
{
    override fun toString(): String
    {
        return leagueName
    }
}
