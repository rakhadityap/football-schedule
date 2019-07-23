package com.example.footbalschedule.leaguedetail

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.LeagueDetail

interface LeagueDetailView: BaseView
{
    fun showLeagueDetail(leagueDetail: LeagueDetail)
}
