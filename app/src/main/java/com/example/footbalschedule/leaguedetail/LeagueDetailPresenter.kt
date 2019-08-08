package com.example.footbalschedule.leaguedetail

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.LeagueDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueDetailPresenter(private val view: LeagueDetailView, private val apiService: ApiService)
{
    var call: Call<LeagueDetailResponse>? = null
    fun getLeagueDetail(id: String)
    {
        call = apiService.getLeagueDetail(id)
        call?.enqueue(object : Callback<LeagueDetailResponse>
        {
            override fun onFailure(call: Call<LeagueDetailResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<LeagueDetailResponse>, response: Response<LeagueDetailResponse>)
            {
                view.showLeagueDetail(response.body()?.leagues?.get(0))
            }

        })
    }

    fun stopRequest(){
        call?.cancel()
    }
}