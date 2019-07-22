package com.example.footbalschedule.leaguedetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.footbalschedule.R
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
        setSupportActionBar(league_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        league_detail_collapsetoolbar.title = "League Detail"

        league_detail_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        league_detail_tabs.setupWithViewPager(league_detail_pager)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}
