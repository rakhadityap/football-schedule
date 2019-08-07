package com.example.footbalschedule.teamdetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.Team
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity(), TeamDetailView
{
    private var menuItem: Menu? = null
    private var isFavorite = false

    private var team: Team? = null
    private val presenter = TeamDetailPresenter(this, apiService)


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(team_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Team Detail"

        team_detail_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        team_detail_tabs.setupWithViewPager(team_detail_pager)

        val idTeam = intent.getStringExtra("idTeam")

        presenter.getTeamDetail(idTeam)
        presenter.checkFavorite(this, idTeam)

    }

    override fun showTeamDetail(team: Team?)
    {
        this.team = team
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

    override fun showError(message: String)
    {
        showToast(this, message)
    }

    override fun setFavoriteState(inFavorite: Boolean)
    {
        isFavorite = inFavorite
        setFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu_favorite_detail, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId)
        {
            android.R.id.home -> finish()
            R.id.add_favorite_menu ->
            {
                if (isFavorite) presenter.removeFromFavorite(this, team)
                else presenter.addToFavorite(this, team)

                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return true
    }

    fun setFavorite()
    {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
    }
}
