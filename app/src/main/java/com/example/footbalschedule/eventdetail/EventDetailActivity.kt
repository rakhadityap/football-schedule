package com.example.footbalschedule.eventdetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.app.*
import com.example.footbalschedule.model.EventDetail
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity(), EventDetailView
{

    private val presenter: EventDetailPresenter by lazy {
        EventDetailPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        supportActionBar?.title = "Event Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val eventId = intent.getStringExtra("eventId")

        event_detail_refresh.refresh()

        presenter.getEventDetail(eventId)

        event_detail_refresh.setOnRefreshListener {
            presenter.getEventDetail(eventId)
        }
    }

    override fun showDetail(eventDetail: EventDetail)
    {
        event_detail_tanggal.text = getDate("${eventDetail.strDate} ${eventDetail.strTime}")
        event_detail_home_team.text = eventDetail.strHomeTeam
        event_detail_away_team.text = eventDetail.strAwayTeam

        event_detail_home_score.text = eventDetail.intHomeScore
        event_detail_away_score.text = eventDetail.intAwayScore

        event_detail_home_formation.text = eventDetail.strHomeFormation
        event_detail_away_formation.text = eventDetail.strAwayFormation

        event_detail_home_goals.text = "${eventDetail.strHomeGoalDetails}".replace(";", "\n")
        event_detail_away_goals.text = "${eventDetail.strAwayGoalDetails}".replace(";", "\n")

        event_detail_home_shots.text = eventDetail.intHomeShots
        event_detail_away_shots.text = eventDetail.intAwayShots

        event_detail_home_keeper.text = eventDetail.strHomeLineupGoalkeeper
        event_detail_away_keeper.text = eventDetail.strAwayLineupGoalkeeper

        event_detail_home_defense.text = "${eventDetail.strHomeLineupDefense}".replace(";", "\n")
        event_detail_away_defense.text = "${eventDetail.strAwayLineupDefense}".replace(";", "\n")

        event_detail_home_midfield.text = "${eventDetail.strHomeLineupMidfield}".replace(";", "\n")
        event_detail_away_midfield.text = "${eventDetail.strAwayLineupMidfield}".replace(";", "\n")

        event_detail_home_forward.text = "${eventDetail.strHomeLineupForward}".replace(";", "\n")
        event_detail_away_forward.text = "${eventDetail.strAwayLineupForward}".replace(";", "\n")

        event_detail_home_subs.text = "${eventDetail.strHomeLineupSubstitutes}".replace(";", "\n")
        event_detail_away_subs.text = "${eventDetail.strAwayLineupSubstitutes}".replace(";", "\n")

        presenter.getClubBadge(eventDetail.idHomeTeam ?: "0000", eventDetail.idAwayTeam ?: "0000")

    }

    override fun showBadges(homeURL: String, awayURL: String)
    {
        Glide.with(this).load(homeURL).into(event_detail_home_image)
        Glide.with(this).load(awayURL).into(event_detail_away_image)
        event_detail_refresh.done()
        event_detail_container.visible()
    }

    override fun showError(message: String)
    {
        showToast(this, message)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId)
        {
            android.R.id.home -> finish()
        }
        return true
    }
}
