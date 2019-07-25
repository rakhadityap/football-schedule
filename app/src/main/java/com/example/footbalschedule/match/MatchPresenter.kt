package com.example.footbalschedule.match

import com.example.footbalschedule.app.Const
import com.example.footbalschedule.model.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenter(private val view: MatchView) {
    fun getFutureMatches(id: String) {
        val call: Call<MatchResponse> = Const.apiService.getSchedule(id)
        call.enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                try {
                    view.showFutureMatches(response.body()!!.events)
                } catch (e: Exception) {
                    view.showError("Empty Match Data")
                }
            }

        })
    }

    fun getPastMatches(id: String) {
        val call: Call<MatchResponse> = Const.apiService.getPastEvents(id)
        call.enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<MatchResponse>,
                response: Response<MatchResponse>
            ) {
                try {
                    view.showPastMatches(response.body()!!.events)
                } catch (e: Exception) {
                    view.showError("Empty Match Data")
                }
            }

        })
    }
}