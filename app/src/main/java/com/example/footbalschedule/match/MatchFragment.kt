package com.example.footbalschedule.match


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalschedule.R
import com.example.footbalschedule.app.*
import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.eventdetail.EventDetailActivity
import com.example.footbalschedule.model.Match
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_match.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment(), MatchView {
    private lateinit var mContext: Context
    private val nextMatches: MutableList<Match> = mutableListOf()
    private val pastMatches: MutableList<Match> = mutableListOf()
    private lateinit var nextAdapter: MatchRecyclerviewAdapter
    private lateinit var pastAdapter: MatchRecyclerviewAdapter
    val presenter = MatchPresenter(this, apiService)

    private var refresh = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        val leagueId: String = activity?.intent?.getStringExtra("idLeague") ?: "0000"

        mContext = activity!!.applicationContext

        pastAdapter = MatchRecyclerviewAdapter(pastMatches, mContext) { match ->
            val intent = Intent(mContext, EventDetailActivity::class.java).apply {
                putExtra("eventId", match.idEvent)
            }
            startActivity(intent)
        }

        nextAdapter = MatchRecyclerviewAdapter(nextMatches, mContext) { match ->
            val intent = Intent(mContext, EventDetailActivity::class.java).apply {
                putExtra("eventId", match.idEvent)
            }
            startActivity(intent)
        }



        view.last_match_recyclerview.layoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        view.next_match_recyclerview.layoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)

        view.last_match_recyclerview.adapter = pastAdapter
        view.next_match_recyclerview.adapter = nextAdapter

        snapRecyclerView(view.next_match_recyclerview)
        snapRecyclerView(view.last_match_recyclerview)


        view.match_refreshview.setOnRefreshListener {
            presenter.getFutureMatches(leagueId)
            presenter.getPastMatches(leagueId)
        }
        view.match_refreshview.refresh()

        presenter.getFutureMatches(leagueId)
        presenter.getPastMatches(leagueId)
        return view
    }

    override fun showFutureMatches(matches: List<Match>?) {
        matches?.let {
            nextMatches.clear()
            nextMatches.addAll(it)
            nextAdapter.notifyDataSetChanged()
            next_match_group.visible()
        }
        stopRefresh()
    }

    override fun showPastMatches(matches: List<Match>?) {
        matches?.let {
            pastMatches.clear()
            pastMatches.addAll(it)
            pastAdapter.notifyDataSetChanged()
            last_match_group.visible()
        }
        stopRefresh()
    }

    fun stopRefresh() {
        refresh++
        if (refresh == 2) {
            refresh = 0
            this.view?.match_refreshview?.done()
        }
    }

    override fun showError(message: String?) {
        showToast(mContext, message?:"Error")
    }

    override fun onPause() {
        super.onPause()

        presenter.stopRequest()
    }

}
