package com.example.footbalschedule.standings

import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.model.StandingsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StandingsPresenter(private val view: StandingsView)
{
    private lateinit var call: Call<StandingsResponse>
    fun getStandings(leagueId: String)
    {
        call = apiService.getStandings(leagueId)
        call.enqueue(object : Callback<StandingsResponse>{
            override fun onFailure(call: Call<StandingsResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<StandingsResponse>, response: Response<StandingsResponse>)
            {
                response.body()?.table.let{
                    view.showStandings(it)
                }
            }

        })
    }
}