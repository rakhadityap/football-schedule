package com.example.footbalschedule.teamdetail

import android.content.Context
import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.app.room.FootballDatabase
import com.example.footbalschedule.model.Team
import com.example.footbalschedule.model.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamDetailPresenter(private val view: TeamDetailView, private val apiService: ApiService)
{
    private lateinit var call: Call<TeamResponse>

    fun getTeamDetail(id: String)
    {
        call = apiService.getTeam(id)
        call.enqueue(object : Callback<TeamResponse>
        {
            override fun onFailure(call: Call<TeamResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>)
            {
                view.showTeamDetail(response.body()?.teams?.get(0))
            }

        })
    }

    fun addToFavorite(context: Context, team: Team?)
    {
        team?.let {
            GlobalScope.launch(Dispatchers.Main) {
                FootballDatabase(context).teamDao().insertTeam(it)
            }
        }
    }

    fun removeFromFavorite(context: Context, team: Team?)
    {
        team?.let {
            GlobalScope.launch(Dispatchers.Main) {
                FootballDatabase(context).teamDao().removeTeam(it)
            }
        }
    }

    fun checkFavorite(context: Context, idTeam: String)
    {
        GlobalScope.launch(Dispatchers.Main) {
            val teamCount = FootballDatabase(context).teamDao().checkTeam(idTeam)
            if (teamCount > 0) view.setFavoriteState(true)
            else view.setFavoriteState(false)
        }
    }
}