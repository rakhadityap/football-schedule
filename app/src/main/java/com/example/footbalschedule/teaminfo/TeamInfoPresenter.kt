package com.example.footbalschedule.teaminfo

import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamInfoPresenter(val view: TeamInfoView) {
    private lateinit var call: Call<TeamResponse>

    fun getTeamInfo(id: String){
        call = apiService.getTeam(id)
        call.enqueue(object: Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                view.showTeamInfo(response.body()?.teams?.get(0))
            }

        })
    }
}