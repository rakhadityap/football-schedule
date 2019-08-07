package com.example.footbalschedule.standings

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.Standings

interface StandingsView : BaseView
{
    fun showStandings(standings: List<Standings>?)
}