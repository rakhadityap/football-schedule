package com.example.footbalschedule.standings


import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.footbalschedule.model.Standings
import kotlinx.android.synthetic.main.fragment_standings.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class StandingsFragment : Fragment(), StandingsView
{
    private var mContext: Context? = null
    private val datas: MutableList<Standings> = mutableListOf()
    private lateinit var adapter: StandingsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_standings, container, false)
        val presenter = StandingsPresenter(this, apiService)

        mContext = activity?.applicationContext
        adapter = StandingsRecyclerViewAdapter(datas, mContext!!){
        }
        val leagueId: String = activity?.intent?.getStringExtra("idLeague") ?: "0000"
        Log.d("League ID", leagueId)

        view.standings_recyclerview.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        view.standings_recyclerview.adapter = adapter

        view.standings_refresh.setOnRefreshListener {
            presenter.getStandings(leagueId)
        }

        view.standings_refresh.refresh()

        presenter.getStandings(leagueId)

        return view
    }

    override fun showStandings(standings: List<Standings>?)
    {
        Log.d("View", "Show Standings!!!")
        standings?.let {
            datas.clear()
            datas.addAll(it)
            adapter.notifyDataSetChanged()
        }
        this.view?.standings_refresh?.done()
    }

    override fun showError(message: String)
    {
        showToast(mContext!!, message)
    }
}
