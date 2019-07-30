package com.example.footbalschedule.teaminfo

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.Team

interface TeamInfoView : BaseView {
    fun showTeamInfo(team: Team?)
}
