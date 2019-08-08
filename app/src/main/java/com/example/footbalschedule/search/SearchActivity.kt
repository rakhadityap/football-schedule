package com.example.footbalschedule.search

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalschedule.R
import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.eventdetail.EventDetailActivity
import com.example.footbalschedule.match.MatchRecyclerviewAdapter
import com.example.footbalschedule.model.Match
import com.example.footbalschedule.model.Team
import com.example.footbalschedule.team.TeamRecyclerViewAdapter
import com.example.footbalschedule.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchView
{
    val fragmentSource by lazy {
        intent.getIntExtra("source", 0)
    }

    val matchList: MutableList<Match> = mutableListOf()
    val teamList: MutableList<Team> = mutableListOf()

    val adapter by lazy {
        when (fragmentSource)
        {
            0, 1 ->
            {
                MatchRecyclerviewAdapter(matchList, this) {
                    startActivity(Intent(this, EventDetailActivity::class.java).apply {
                        putExtra("eventId", it.idEvent)
                    })
                }
            }
            else ->
            {
                TeamRecyclerViewAdapter(teamList, this) {
                    startActivity(Intent(this, TeamDetailActivity::class.java).apply {
                        putExtra("idTeam", it.idTeam)
                    })
                }
            }
        }
    }

    val presenter = SearchPresenter(this, apiService)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        search_recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        search_recyclerview.adapter = adapter
    }

    override fun showError(message: String?) {
        showToast(this, message ?: "Error")
    }

    override fun showSearchResult(datas: List<Any>?)
    {
        datas?.let {
            if (fragmentSource == 0 || fragmentSource == 1)
            {
                matchList.clear()
                matchList.addAll(it as List<Match>)
            } else
            {
                teamList.clear()
                teamList.addAll(it as List<Team>)
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.search_menu_detail, menu)

        val search = menu?.findItem(R.id.search_id)
        val searchView = search?.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true

        when (fragmentSource)
        {
            0, 1 -> searchView.queryHint = "Search Match"
            else -> searchView.queryHint = "Search Team"
        }

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean
            {
                if (query == "")
                {
                    matchList.clear()
                    teamList.clear()
                    adapter.notifyDataSetChanged()
                } else
                {
                    when (fragmentSource)
                    {
                        0, 1 -> presenter.searchMatch(query ?: "")
                        else -> presenter.searchTeam(query ?: "")
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                if (newText == "")
                {
                    matchList.clear()
                    teamList.clear()
                    adapter.notifyDataSetChanged()
                } else
                {
                    when (fragmentSource)
                    {
                        0, 1 -> presenter.searchMatch(newText ?: "")
                        else -> presenter.searchTeam(newText ?: "")
                    }
                }
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId)
        {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onPause() {
        super.onPause()

        presenter.stopRequest()
    }
}
