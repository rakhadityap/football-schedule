package com.example.footbalschedule.eventdetail

import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.model.ClubResponse
import com.example.footbalschedule.model.EventDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailPresenter(private val view: EventDetailView)
{
    private lateinit var detailCall: Call<EventDetailResponse>
    private var homeCall: Call<ClubResponse>? = null
    private var awayCall: Call<ClubResponse>? = null

    var ready = 0

    fun getEventDetail(eventId: String)
    {
        detailCall = apiService.getEventDetails(eventId)
        detailCall.enqueue(object : Callback<EventDetailResponse>
        {
            override fun onFailure(call: Call<EventDetailResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<EventDetailResponse>, response: Response<EventDetailResponse>)
            {
                view.showDetail(response.body()?.events!![0])
            }

        })
    }

    fun getClubBadge(homeId: String, awayId: String)
    {
        homeCall = apiService?.getClub(homeId)
        awayCall = apiService?.getClub(awayId)

        var homeURL = ""
        var awayURL = ""

        homeCall?.enqueue(object : Callback<ClubResponse>
        {
            override fun onResponse(call: Call<ClubResponse>, response: Response<ClubResponse>)
            {
                homeURL = response.body()?.teams?.get(0)?.strTeamBadge ?: ""

                awayCall?.enqueue(object : Callback<ClubResponse>
                {
                    override fun onResponse(call: Call<ClubResponse>, response: Response<ClubResponse>)
                    {
                        awayURL = response.body()?.teams?.get(0)?.strTeamBadge ?: ""

                        view.showBadges(homeURL, awayURL)

                    }

                    override fun onFailure(call: Call<ClubResponse>, t: Throwable)
                    {
                        view.showError(t.localizedMessage)
                    }

                })

            }

            override fun onFailure(call: Call<ClubResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

        })
    }
}