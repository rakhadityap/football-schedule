package com.example.footbalschedule.search

import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.MatchResponse
import com.example.footbalschedule.model.MatchSearchResponse
import com.example.footbalschedule.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(val view: SearchView) {
    private lateinit var callTeam: Call<TeamResponse>
    private lateinit var callMatch: Call<MatchSearchResponse>

    fun searchTeam(key: String){
        callTeam = apiService.searchTeams(key)
        callTeam.enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                view.showSearchResult(response.body()?.teams)
            }
        })
    }

    fun searchMatch(key: String){
        callMatch = apiService.searchMatch(key)
        callMatch.enqueue(object : Callback<MatchSearchResponse>{
            override fun onFailure(call: Call<MatchSearchResponse>, t: Throwable) {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<MatchSearchResponse>, response: Response<MatchSearchResponse>) {
                view.showSearchResult(response.body()?.event)
            }

        })
    }
}