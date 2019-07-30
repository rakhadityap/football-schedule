package com.example.footbalschedule.player

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.model.Player
import kotlinx.android.synthetic.main.generic_list_item.view.*

class PlayerRecyclerViewAdapter(
    private val datas: List<Player>,
    private val mContext: Context,
    private val listener: (Player) -> Unit
) : RecyclerView.Adapter<PlayerRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.generic_list_item, parent, false))

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(datas[position], listener)

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val playerName = view.generic_list_title
        private val playerPos = view.generic_list_subtitle
        private val playerBadge = view.generic_list_badge

        fun bind(player: Player, listener: (Player) -> Unit) {
            playerName.text = player.strPlayer
            playerPos.text = player.strPosition
            Glide.with(view)
                .load(player.strCutout)
                .into(playerBadge)
            view.setOnClickListener {
                listener(player)
            }
        }
    }

}