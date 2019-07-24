package com.example.footbalschedule.match

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.Match

interface MatchView: BaseView
{
    fun showFutureMatches(matches: List<Match>)
    fun showPastMatches(matches: List<Match>)
}