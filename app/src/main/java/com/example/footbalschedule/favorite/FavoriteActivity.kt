package com.example.footbalschedule.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalschedule.R
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.eventdetail.EventDetailActivity
import com.example.footbalschedule.match.MatchRecyclerviewAdapter
import com.example.footbalschedule.model.Match
import com.example.footbalschedule.model.Team
import com.example.footbalschedule.team.TeamRecyclerViewAdapter
import com.example.footbalschedule.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity(), FavoriteView
{


    var menuPosition = 0

    val teamList: MutableList<Team> = mutableListOf()
    val matchList: MutableList<Match> = mutableListOf()

    val teamAdapter: TeamRecyclerViewAdapter by lazy {
        TeamRecyclerViewAdapter(teamList, this) {
            val intent = Intent(this, TeamDetailActivity::class.java).apply {
                putExtra("idTeam", it.idTeam)
            }
            startActivity(intent)
        }
    }
    val matchAdapter: MatchRecyclerviewAdapter by lazy {
        MatchRecyclerviewAdapter(matchList, this) {
            val intent = Intent(this, EventDetailActivity::class.java).apply {
                putExtra("eventId", it.idEvent)
            }
            startActivity(intent)
        }
    }

    val presenter: FavoritePresenter = FavoritePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favorite_recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        favorite_bottombar.setOnNavigationItemSelectedListener {
            when (it.itemId)
            {
                R.id.favorite_event_menu ->
                {
                    menuPosition = 0
                    presenter.getFavoriteMatch(this)
                }
                R.id.favorite_team_menu ->
                {
                    menuPosition = 1
                    presenter.getFavoriteTeam(this)
                }
            }
            true
        }

        presenter.getFavoriteMatch(this)
    }

    override fun showFavorite(datas: List<Any>?)
    {
        when (menuPosition)
        {
            0 ->
            {
                favorite_recyclerview.adapter = matchAdapter
                matchList.clear()
                matchList.addAll(datas as List<Match>)
                matchAdapter.notifyDataSetChanged()
            }
            1 ->
            {
                favorite_recyclerview.adapter = teamAdapter
                teamList.clear()
                teamList.addAll(datas as List<Team>)
                matchAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun showError(message: String)
    {
        showToast(this, message)
    }

    override fun onResume()
    {
        super.onResume()
        when (menuPosition)
        {
            0 -> presenter.getFavoriteMatch(this)
            1 -> presenter.getFavoriteTeam(this)
        }
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
