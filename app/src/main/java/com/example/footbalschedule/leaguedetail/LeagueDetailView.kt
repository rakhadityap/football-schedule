package com.example.footbalschedule.leaguedetail

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.League

interface LeagueDetailView : BaseView
{
    fun showLeagueDetail(league: League?)
}
