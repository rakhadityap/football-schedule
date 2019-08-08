package com.example.footbalschedule.search

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.MatchSearchResponse
import com.example.footbalschedule.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(val view: SearchView, private val apiService: ApiService)
{
    var callTeam: Call<TeamResponse>? = null
    var callMatch: Call<MatchSearchResponse>? = null

    fun searchTeam(key: String)
    {
        callTeam = apiService.searchTeams(key)
        callTeam?.enqueue(object : Callback<TeamResponse>
        {
            override fun onFailure(call: Call<TeamResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>)
            {
                view.showSearchResult(response.body()?.teams)
            }
        })
    }

    fun searchMatch(key: String)
    {
        callMatch = apiService.searchMatch(key)
        callMatch?.enqueue(object : Callback<MatchSearchResponse>
        {
            override fun onFailure(call: Call<MatchSearchResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<MatchSearchResponse>, response: Response<MatchSearchResponse>)
            {
                view.showSearchResult(response.body()?.event)
            }

        })
    }

    fun stopRequest(){
        callMatch?.cancel()
        callTeam?.cancel()
    }
}