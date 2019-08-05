package com.example.footbalschedule.player


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalschedule.R
import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.app.done
import com.example.footbalschedule.app.refresh
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.Player
import com.example.footbalschedule.playerdetail.PlayerDetailActivity
import kotlinx.android.synthetic.main.fragment_player.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class PlayerFragment : Fragment(), PlayerView
{
    private val players: MutableList<Player> = mutableListOf()
    private val mContext: Context by lazy{
        activity!!.applicationContext
    }
    private val adapter: PlayerRecyclerViewAdapter by lazy {
        PlayerRecyclerViewAdapter(players, mContext){
            val intent = Intent(mContext, PlayerDetailActivity::class.java).apply {
                putExtra("idPlayer", it.idPlayer)
            }
            startActivity(intent)
        }
    }
    private val idTeam:String by lazy{
        activity!!.intent.getStringExtra("idTeam")
    }

    private val presenter: PlayerPresenter by lazy{
        PlayerPresenter(this, apiService)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_player, container, false)

        view.player_recyclerview.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        view.player_recyclerview.adapter = adapter

        view.player_refresh.setOnRefreshListener {
            presenter.getPlayers(idTeam)
        }

        view.player_refresh.refresh()

        presenter.getPlayers(idTeam)
        return view
    }

    override fun showPlayers(players: List<Player>?) {
        players?.let{
            this.players.clear()
            this.players.addAll(it)
            adapter.notifyDataSetChanged()
        }
        this.view?.player_refresh?.done()
    }

    override fun showError(message: String) {
        showToast(mContext, message)
    }
}
