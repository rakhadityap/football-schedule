package com.example.footbalschedule.leaguedetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.LeagueDetail
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailView
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
        setSupportActionBar(league_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "League Detail"

        league_detail_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        league_detail_tabs.setupWithViewPager(league_detail_pager)

        val leagueId = intent.getStringExtra("leagueId")

        val presenter = LeagueDetailPresenter(this)
        presenter.getLeagueDetail(leagueId)
    }

    override fun showLeagueDetail(leagueDetail: LeagueDetail)
    {
        Glide.with(this)
                .load(leagueDetail.strLogo)
                .into(league_detail_banner)
    }

    override fun showError(message: String)
    {
        showToast(this, message)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}
