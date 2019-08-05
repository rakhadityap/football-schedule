package com.example.footbalschedule.league

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.League

interface LeagueView: BaseView
{
    fun showLeague(leagues: List<League>?)
}