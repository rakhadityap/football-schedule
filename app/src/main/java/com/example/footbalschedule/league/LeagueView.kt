package com.example.footbalschedule.league

import com.example.footbalschedule.model.League

interface LeagueView
{
    fun showLeague(leagues: List<League>)
}