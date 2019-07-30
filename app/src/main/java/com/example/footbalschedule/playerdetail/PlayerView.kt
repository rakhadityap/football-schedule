package com.example.footbalschedule.playerdetail

import com.example.footbalschedule.app.BaseView
import com.example.footbalschedule.model.Player

interface PlayerView: BaseView {
    fun showPlayerDetail(player: Player?)
}