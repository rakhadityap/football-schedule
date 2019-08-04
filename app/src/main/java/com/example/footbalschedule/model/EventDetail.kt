package com.example.footbalschedule.model


data class EventDetail(
    val idEvent: String,
    val strDate: String?,
    val strTime: String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intHomeScore: String?,
    val intAwayScore: String?,
    val strAwayFormation: String?,
    val strHomeFormation: String?,
    val strHomeGoalDetails: String?,
    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupMidfield: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupSubstitutes: String?,
    val strAwayGoalDetails: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupMidfield: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupSubstitutes: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val strEvent: String?,
    val intHomeShots: String?,
    val intAwayShots: String?,
    var strHomeBadge: String = "",
    var strAwayBadge: String = ""
)
