package com.example.footbalschedule.player

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.Player

interface PlayerView : BaseView
{
    fun showPlayers(players: List<Player>?)
}
