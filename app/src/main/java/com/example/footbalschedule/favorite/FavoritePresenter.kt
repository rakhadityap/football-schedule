package com.example.footbalschedule.favorite

import android.content.Context
import com.example.footbalschedule.app.room.FootballDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritePresenter(val view: FavoriteView)
{

    fun getFavoriteMatch(context: Context)
    {
        GlobalScope.launch(Dispatchers.Main) {
            val data = FootballDatabase(context).matchDao().getAll()
            view.showFavorite(data)
        }
    }

    fun getFavoriteTeam(context: Context)
    {
        GlobalScope.launch(Dispatchers.Main) {
            val data = FootballDatabase(context).teamDao().getAll()
            view.showFavorite(data)
        }
    }
}
