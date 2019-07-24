package com.example.footbalschedule.match

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalschedule.R
import com.example.footbalschedule.app.getDate
import com.example.footbalschedule.model.Match
import kotlinx.android.synthetic.main.match_list_item.view.*

class MatchRecyclerviewAdapter(
    private val matches: List<Match>,
    private val context: Context,
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<MatchRecyclerviewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list_item, parent, false))

    override fun getItemCount(): Int = matches.size

    override fun getItemId(position: Int): Long = matches[position].scheduleId.toLong()

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(matches[position], listener)

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {
        private val eventDatetime = view.match_date_textview
        private val eventHomeTeam = view.match_home_team
        private val eventAwayTeam = view.match_away_team
        private val eventHomeScore = view.match_home_score
        private val eventAwayScore = view.match_away_score


        fun bind(match: Match, listener: (Match) -> Unit){
            eventDatetime.text = getDate("${match.scheduleDate} ${match.scheduleTime}")
            eventHomeTeam.text = match.homeTeam
            eventHomeScore.text = match.homeScore
            eventAwayTeam.text = match.awayTeam
            eventAwayScore.text = match.awayScore

            view.setOnClickListener { listener(match) }
        }
    }
}