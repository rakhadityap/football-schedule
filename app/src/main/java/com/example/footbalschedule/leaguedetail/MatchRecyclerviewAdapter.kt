package com.example.footbalschedule.leaguedetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalschedule.R
import com.example.footbalschedule.model.Match

class MatchRecyclerviewAdapter(
    private val matches: List<Match>,
    private val context: Context,
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<MatchRecyclerviewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        MatchRecyclerviewAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.match_list_item, parent, false))

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {

    }
}