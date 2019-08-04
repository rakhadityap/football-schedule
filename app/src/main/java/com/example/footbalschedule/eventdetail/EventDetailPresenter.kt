package com.example.footbalschedule.eventdetail

import android.content.Context
import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.app.room.FootballDatabase
import com.example.footbalschedule.model.EventDetail
import com.example.footbalschedule.model.EventDetailResponse
import com.example.footbalschedule.model.Match
import com.example.footbalschedule.model.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailPresenter(private val view: EventDetailView) {
    private lateinit var detailCall: Call<EventDetailResponse>
    private var homeCall: Call<TeamResponse>? = null
    private var awayCall: Call<TeamResponse>? = null

    fun getEventDetail(eventId: String) {
        detailCall = apiService.getEventDetails(eventId)
        detailCall.enqueue(object : Callback<EventDetailResponse> {
            override fun onFailure(call: Call<EventDetailResponse>, t: Throwable) {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<EventDetailResponse>, response: Response<EventDetailResponse>) {
                view.showDetail(response.body()?.events!![0])
            }

        })
    }

    fun getClubBadge(homeId: String, awayId: String) {
        homeCall = apiService?.getTeam(homeId)
        awayCall = apiService?.getTeam(awayId)

        var homeURL = ""
        var awayURL = ""

        homeCall?.enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                homeURL = response.body()?.teams?.get(0)?.strTeamBadge ?: ""

                awayCall?.enqueue(object : Callback<TeamResponse> {
                    override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                        awayURL = response.body()?.teams?.get(0)?.strTeamBadge ?: ""

                        view.showBadges(homeURL, awayURL)

                    }

                    override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                        view.showError(t.localizedMessage)
                    }

                })

            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                view.showError(t.localizedMessage)
            }

        })
    }

    fun addToFavorite(context: Context, eventDetail: EventDetail?) {
        eventDetail?.let {
            val match = Match(
                idEvent = it.idEvent,
                idHomeTeam = it.idHomeTeam!!,
                idAwayTeam = it.idAwayTeam!!,
                intAwayScore = it.intAwayScore!!,
                intHomeScore = it.intHomeScore!!,
                strAwayTeam = it.strAwayTeam!!,
                strHomeTeam = it.strHomeTeam!!,
                strDate = it.strDate!!,
                strTime = it.strTime!!
            )
            GlobalScope.launch(Dispatchers.Main) {
                FootballDatabase(context).matchDao().insertMatch(match)
            }
        }
    }

    fun removeFromFavorite(context: Context, eventDetail: EventDetail?) {
        eventDetail?.let {
            val match = Match(
                idEvent = it.idEvent,
                idHomeTeam = it.idHomeTeam!!,
                idAwayTeam = it.idAwayTeam!!,
                intAwayScore = it.intAwayScore!!,
                intHomeScore = it.intHomeScore!!,
                strAwayTeam = it.strAwayTeam!!,
                strHomeTeam = it.strHomeTeam!!,
                strDate = it.strDate!!,
                strTime = it.strTime!!
            )
            GlobalScope.launch(Dispatchers.Main) {
                FootballDatabase(context).matchDao().removeMatch(match)
            }
        }
    }

    fun getFavoriteState(context: Context, idEvent: String) {

        GlobalScope.launch(Dispatchers.Main) {
            val matchCount = FootballDatabase(context).matchDao().checkMatch(idEvent)
            if(matchCount > 0) view.setFavoriteState(true)
            else view.setFavoriteState(false)
        }

    }
}