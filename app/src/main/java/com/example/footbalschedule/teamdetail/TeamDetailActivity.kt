package com.example.footbalschedule.teamdetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.Team
import com.example.footbalschedule.team.TeamPresenter
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(team_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Team Detail"

        team_detail_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        team_detail_tabs.setupWithViewPager(team_detail_pager)

        val idTeam = intent.getStringExtra("idTeam")

        val presenter = TeamDetailPresenter(this)

        presenter.getTeamDetail(idTeam)

    }

    override fun showTeamDetail(team: Team?) {
        team?.let {
            team_detail_name.text = it.strTeam
            team_detail_location.text = it.strStadiumLocation
            Glide.with(this)
                .load(it.strTeamBadge)
                .into(team_detail_badge)
            Glide.with(this)
                .load(it.strTeamBanner)
                .into(team_detail_banner)
        }
    }

    override fun showError(message: String) {
        showToast(this, message)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}
