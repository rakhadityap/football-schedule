package com.example.footbalschedule.league

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalschedule.R
import com.example.footbalschedule.app.done
import com.example.footbalschedule.app.refresh
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.leaguedetail.LeagueDetailActivity
import com.example.footbalschedule.model.League
import kotlinx.android.synthetic.main.activity_main.*

class LeagueActivity : AppCompatActivity(), LeagueView
{
    val adapter: LeagueRecyclerviewAdapter

    val leagueList: MutableList<League> = mutableListOf()
    val leaguePresenter = LeaguePresenter(this)
    init
    {
        adapter = LeagueRecyclerviewAdapter(leagueList, this) { league ->
            val intent = Intent(this, LeagueDetailActivity::class.java).apply {
                putExtra("leagueId", league.leagueId)
            }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Leagues"

        league_list_recview.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        league_list_recview.adapter = adapter
        league_list_refreshview.setOnRefreshListener {
            leaguePresenter.getLeagues()
        }
        league_list_refreshview.refresh()
        leaguePresenter.getLeagues()
    }

    override fun showLeague(leagues: List<League>)
    {
        leagueList.clear()
        leagueList.addAll(leagues)
        adapter.notifyDataSetChanged()
        league_list_refreshview.done()
    }

    override fun showError(message: String)
    {
        showToast(this, message)
        league_list_refreshview.done()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
