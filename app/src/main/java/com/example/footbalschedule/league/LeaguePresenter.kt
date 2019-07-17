package com.example.footbalschedule.league

import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.model.LeagueResponse
import kotlinx.android.synthetic.main.league_list_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguePresenter(private val view: LeagueView)
{
    public fun getLeagues()
    {
        val call: Call<LeagueResponse> = apiService.getLeagueList()
        call.enqueue(object : Callback<LeagueResponse>
        {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable)
            {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>)
            {
                view.showLeague(response.body()!!.countrys)
            }

        })
    }
}