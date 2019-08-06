package com.example.footbalschedule.eventdetail

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.EventDetail

interface EventDetailView: BaseView
{
    fun showDetail(eventDetail: EventDetail?)
    fun showBadges(homeURL: String?, awayURL: String?)
    fun setFavoriteState(inFavorite: Boolean)
}