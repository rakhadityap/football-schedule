package com.example.footbalschedule.standings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalschedule.R
import com.example.footbalschedule.model.Standings
import kotlinx.android.synthetic.main.standings_list_item.view.*

class StandingsRecyclerViewAdapter(
    private val datas: List<Standings>,
    private val mContext: Context,
    private val listener: (Standings) -> Unit
) : RecyclerView.Adapter<StandingsRecyclerViewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.standings_list_item, parent, false))

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(datas[position], listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        private val teamPos = view.standings_position
        private val teamName = view.standings_team
        private val teamMatch = view.standings_matches
        private val teamWin = view.standings_win
        private val teamDraw = view.standings_draw
        private val teamLoss = view.standings_loss
        private val teamGF = view.standings_goalsfor
        private val teamGA = view.standings_goalsagainst
        private val teamGD = view.standings_goalsdiff
        private val teamPoints = view.standings_point

        fun bind(standing: Standings, listener: (Standings) -> Unit)
        {
            teamPos.text = "${this.adapterPosition + 1}"
            teamName.text = standing.teamName
            teamMatch.text = "${standing.played}"
            teamWin.text = "${standing.win}"
            teamDraw.text = "${standing.draw}"
            teamLoss.text = "${standing.loss}"
            teamGF.text = "${standing.goalsFor}"
            teamGA.text = "${standing.goalsAgainst}"
            teamGD.text = "${standing.goalsDiff}"
            teamPoints.text = "${standing.total}"
            itemView.setOnClickListener { listener(standing) }
        }
    }
}