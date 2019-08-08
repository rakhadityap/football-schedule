package com.example.footbalschedule.playerdetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.app.done
import com.example.footbalschedule.app.refresh
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.Player
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerView
{
    val presenter = PlayerDetailPresenter(this, apiService)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        setSupportActionBar(player_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Player Detail"

        val idPlayer = intent?.getStringExtra("idPlayer") ?: "0000"

        player_detail_refresh.setOnRefreshListener {
            presenter.getPlayerDetail(idPlayer)
        }

        player_detail_refresh.refresh()

        presenter.getPlayerDetail(idPlayer)
    }

    override fun showPlayerDetail(player: Player?)
    {
        player?.let {
            player_detail_name.text = it.strPlayer
            player_detail_position.text = it.strPosition
            player_detail_description.text = it.strDescriptionEN

            Glide.with(this)
                .load(it.strFanart1)
                .into(player_detail_banner)

            Glide.with(this)
                .load(it.strCutout)
                .into(player_detail_photo)
        }
        player_detail_refresh.done()
    }

    override fun showError(message: String?) {
        showToast(this, message?:"Error")
        player_detail_refresh.done()
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
