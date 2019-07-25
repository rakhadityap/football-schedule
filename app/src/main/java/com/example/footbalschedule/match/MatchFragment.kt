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
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.model.EventDetail
import com.example.footbalschedule.model.Match
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        mContext = activity!!.applicationContext

        pastAdapter = MatchRecyclerviewAdapter(pastMatches, mContext) { match ->
            val intent = Intent(mContext, EventDetail::class.java).apply {
                putExtra("eventId", match.scheduleId)
            }
            startActivity(intent)
        }

        nextAdapter = MatchRecyclerviewAdapter(nextMatches, mContext) { match ->
            val intent = Intent(mContext, EventDetail::class.java).apply {
                putExtra("eventId", match.scheduleId)
            }
            startActivity(intent)
        }

        val presenter = MatchPresenter(this)
        val leagueId: String = activity?.intent?.getStringExtra("leagueId") ?: "0000"

        view.last_match_recyclerview.layoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        view.next_match_recyclerview.layoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)

        view.last_match_recyclerview.adapter = pastAdapter
        view.next_match_recyclerview.adapter = nextAdapter

        presenter.getFutureMatches(leagueId)
        presenter.getPastMatches(leagueId)

        return view
    }

    override fun showFutureMatches(matches: List<Match>) {
        nextMatches.clear()
        nextMatches.addAll(matches)
        nextAdapter.notifyDataSetChanged()
    }

    override fun showPastMatches(matches: List<Match>) {
        pastMatches.clear()
        pastMatches.addAll(matches)
        pastAdapter.notifyDataSetChanged()
    }

    override fun showError(message: String) {
        showToast(activity!!.applicationContext, message)
        Log.e("Match", message)
    }


}
