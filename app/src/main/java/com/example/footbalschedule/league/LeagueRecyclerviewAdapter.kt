package com.example.footbalschedule.league

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.model.League
import kotlinx.android.synthetic.main.league_list_item.view.*

class LeagueRecyclerviewAdapter(
    private val datas: List<League>,
    private val context: Context,
    private val listener: (League) -> Unit
) : RecyclerView.Adapter<LeagueRecyclerviewAdapter.ViewHolder>()
{
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.league_list_item, parent, false))


    override fun getItemId(position: Int): Long = datas[position].leagueId.toLongOrNull() ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(datas[position], listener)


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {
        private val leagueBadge = view.league_list_logo_iv
        private val leagueName = view.league_list_name_tv

        fun bind(data: League, listener: (League) -> Unit)
        {
            leagueName.text = data.leagueName
            Glide.with(view)
                .load(data.leagueBadge)
                .into(leagueBadge)
            itemView.setOnClickListener { listener(data) }
        }
    }
}