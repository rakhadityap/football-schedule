package com.example.footbalschedule.teaminfo

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.Team

interface TeamView: BaseView
{
    fun showTeams(data: List<Team>?)
}