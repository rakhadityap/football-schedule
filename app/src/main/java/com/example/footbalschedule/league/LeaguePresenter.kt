package com.example.footbalschedule.league

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguePresenter(private val view: LeagueView, private val apiService: ApiService)
{
    var call: Call<LeagueResponse>? = null
    fun getLeagues()
    {
        call = apiService.getLeagueList()
        call?.enqueue(object : Callback<LeagueResponse>
        {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>)
            {
                view.showLeague(response.body()?.countrys)
            }

        })
    }

    fun stopRequest(){
        call?.cancel()
    }
}