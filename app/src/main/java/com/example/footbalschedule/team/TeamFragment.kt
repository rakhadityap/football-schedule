package com.example.footbalschedule.team


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
import com.example.footbalschedule.model.Team
import com.example.footbalschedule.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_team.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFragment : Fragment(), TeamView
{
    private var presenter = TeamPresenter(this, apiService)
    private lateinit var adapter: TeamRecyclerViewAdapter
    private lateinit var mContext: Context
    private val teams: MutableList<Team> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        mContext = activity!!.applicationContext
        adapter = TeamRecyclerViewAdapter(teams, mContext) {
            val intent = Intent(mContext, TeamDetailActivity::class.java).apply {
                putExtra("idTeam", it.idTeam)
            }
            startActivity(intent)
        }

        val idLeague = activity?.intent?.getStringExtra("idLeague")

        view.team_recyclerview.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        view.team_recyclerview.adapter = adapter


        view.team_refresh.setOnRefreshListener {
            presenter.getTeams(idLeague ?: "0000")
        }

        view.team_refresh.refresh()
        presenter.getTeams(idLeague ?: "0000")
        return view
    }

    override fun showTeams(data: List<Team>?)
    {
        data?.let {
            teams.clear()
            teams.addAll(data)
            adapter.notifyDataSetChanged()
        }
        this.view?.team_refresh?.done()
    }

    override fun showError(message: String?) {
        showToast(mContext, message?:"Error")
    }

    override fun onPause() {
        super.onPause()

        presenter.stopRequest()
    }
}
