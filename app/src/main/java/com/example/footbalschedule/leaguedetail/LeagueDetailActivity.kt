package com.example.footbalschedule.leaguedetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.app.done
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.League
import com.example.footbalschedule.search.SearchActivity
import kotlinx.android.synthetic.main.activity_league_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailView
{
    val presenter = LeagueDetailPresenter(this, apiService)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
        setSupportActionBar(league_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "League Detail"

        league_detail_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        league_detail_tabs.setupWithViewPager(league_detail_pager)

        val leagueId = intent.getStringExtra("idLeague")

        presenter.getLeagueDetail(leagueId)
    }

    override fun showLeagueDetail(league: League?)
    {
        league?.let {
            Glide.with(this)
                .load(it.strFanart1)
                .into(league_detail_banner)
            Glide.with(this)
                .load(it.strBadge)
                .into(league_detail_badge)
            league_detail_name.text = it.strLeague
            league_detail_country.text = it.strCountry
        }
    }

    override fun showError(message: String?) {
        showToast(this, message ?: "Error")

        league_list_refreshview.done()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId)
        {
            android.R.id.home -> finish()
            R.id.search_menu_id -> startActivity(Intent(this, SearchActivity::class.java).apply {
                putExtra("source", league_detail_pager.currentItem)
            })
        }
        return true
    }

    override fun onPause() {
        super.onPause()

        presenter.stopRequest()
    }
}
