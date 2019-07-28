package com.example.footbalschedule.model

import com.google.gson.annotations.SerializedName

data class Standings(
        @SerializedName("name")
        val teamName: String,

        @SerializedName("teamid")
        val teamId: String,

        val played: Int = 0,

        @SerializedName("goalsfor")
        val goalsFor: Int = 0,

        @SerializedName("goalsagainst")
        val goalsAgainst: Int = 0,

        @SerializedName("goalsdifference")
        val goalsDiff: Int = 0,

        val win: Int = 0,

        val draw: Int = 0,

        val loss: Int = 0,

        val total: Int = 0
)
