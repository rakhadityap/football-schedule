package com.example.footbalschedule.match

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenter(private val view: MatchView, private val apiService: ApiService)
{
    private var nextMatchCall: Call<MatchResponse>? = null
    private var pastMatchCall: Call<MatchResponse>? = null

    fun getFutureMatches(id: String)
    {
        nextMatchCall = apiService.getSchedule(id)
        nextMatchCall?.enqueue(object : Callback<MatchResponse>
        {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>)
            {
                view.showFutureMatches(response.body()?.events)
            }
        })
    }

    fun getPastMatches(id: String)
    {
        pastMatchCall = apiService.getPastEvents(id)
        pastMatchCall?.enqueue(object : Callback<MatchResponse>
        {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<MatchResponse>,
                response: Response<MatchResponse>
            )
            {

                view.showPastMatches(response.body()?.events)
            }

        })
    }

    fun cancelRequest(){
        nextMatchCall?.cancel()
        pastMatchCall?.cancel()
    }
}