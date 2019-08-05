package com.example.footbalschedule.team

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamPresenter(val view: TeamView, private val apiService: ApiService)
{
    private lateinit var callTeam: Call<TeamResponse>

    fun getTeams(id: String){
        callTeam = apiService.getTeamList(id)
        callTeam.enqueue(object: Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>)
            {
                view.showTeams(response.body()?.teams)
            }

        })
    }
}