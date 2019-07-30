package com.example.footbalschedule.team

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.model.Team
import kotlinx.android.synthetic.main.team_list_item.view.*

class TeamRecyclerViewAdapter(
        val datas: List<Team>,
        val mContext: Context,
        val listener: (Team) -> Unit) : RecyclerView.Adapter<TeamRecyclerViewAdapter.ViewHolder>()
{
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {
        val teamName = view.team_list_name
        val teamLocation = view.team_list_location
        val teamBadge = view.team_list_badge

        fun bind(data: Team, listener: (Team) -> Unit)
        {
            teamName.text = data.strTeam
            teamLocation.text = data.strStadiumLocation
            Glide.with(view).load(data.strTeamBadge).into(teamBadge)
            view.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.team_list_item, parent, false))

    override fun getItemCount(): Int = datas.size

    override fun getItemId(position: Int): Long = datas[position].idTeam.toLong()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(datas[position], listener)
}