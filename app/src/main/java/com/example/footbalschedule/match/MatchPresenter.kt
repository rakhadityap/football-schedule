package com.example.footbalschedule.match

import com.example.footbalschedule.app.Const
import com.example.footbalschedule.model.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenter(private val view: MatchView)
{
    private lateinit var nextMatchCall: Call<MatchResponse>
    private lateinit var pastMatchCall: Call<MatchResponse>

    fun getFutureMatches(id: String)
    {
        nextMatchCall = Const.apiService.getSchedule(id)
        nextMatchCall.enqueue(object : Callback<MatchResponse>
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
        pastMatchCall = Const.apiService.getPastEvents(id)
        pastMatchCall.enqueue(object : Callback<MatchResponse>
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
        nextMatchCall.cancel()
        pastMatchCall.cancel()
    }
}