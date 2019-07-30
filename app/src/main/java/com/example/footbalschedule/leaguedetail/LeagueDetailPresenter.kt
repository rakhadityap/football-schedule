package com.example.footbalschedule.leaguedetail

import com.example.footbalschedule.app.Const
import com.example.footbalschedule.model.LeagueDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueDetailPresenter(private val view: LeagueDetailView)
{
    fun getLeagueDetail(id: String){
        val call: Call<LeagueDetailResponse> = Const.apiService.getLeagueDetail(id)
        call.enqueue(object : Callback<LeagueDetailResponse>
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

}