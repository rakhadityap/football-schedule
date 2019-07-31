package com.example.footbalschedule.model

data class League(
    val idLeague: String,
    val strLeague: String,
    val strBadge: String,
    val strFanart1: String,
    val strCountry: String
)
{
    override fun toString(): String
    {
        return strLeague
    }
}
