package com.example.footbalschedule.leaguedetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.League
import com.example.footbalschedule.search.SearchActivity
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
        setSupportActionBar(league_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "League Detail"

        league_detail_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        league_detail_tabs.setupWithViewPager(league_detail_pager)

        val leagueId = intent.getStringExtra("idLeague")

        val presenter = LeagueDetailPresenter(this)
        presenter.getLeagueDetail(leagueId)
    }

    override fun showLeagueDetail(league: League?) {
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

    override fun showError(message: String) {
        showToast(this, message)
    }

    fun showMenu(a: Boolean) {
        league_detail_toolbar.menu?.getItem(R.id.search_menu_id)?.setVisible(a)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.search_menu_id -> startActivity(Intent(this, SearchActivity::class.java).apply {
                putExtra("source", league_detail_pager.currentItem)
            })
        }
        return true
    }
}
