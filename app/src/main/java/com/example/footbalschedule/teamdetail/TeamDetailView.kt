package com.example.footbalschedule.teamdetail

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.Team

interface TeamDetailView: BaseView {
    fun showTeamDetail(team: Team?)
    fun setFavoriteState(inFavorite: Boolean)
}