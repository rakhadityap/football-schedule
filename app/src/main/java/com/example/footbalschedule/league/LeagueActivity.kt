package com.example.footbalschedule.league

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.footbalschedule.R
import com.example.footbalschedule.model.League

class LeagueActivity : AppCompatActivity(), LeagueView {
    override fun showLeague(leagues: List<League>)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Leagues"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        return super.onCreateOptionsMenu(menu)
    }
}
