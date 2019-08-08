package com.example.footbalschedule.teaminfo


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.footbalschedule.R
import com.example.footbalschedule.app.Const.apiService
import com.example.footbalschedule.app.done
import com.example.footbalschedule.app.refresh
import com.example.footbalschedule.app.showToast
import com.example.footbalschedule.app.visible
import com.example.footbalschedule.model.Team
import kotlinx.android.synthetic.main.fragment_team_info.view.*

class TeamInfoFragment : Fragment(), TeamInfoView
{
    private val presenter = TeamInfoPresenter(this, apiService)
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_team_info, container, false)
        val idTeam = activity?.intent?.getStringExtra("idTeam") ?: "0000"
        mContext = activity!!.applicationContext

        view.team_info_refresh.setOnRefreshListener {
            presenter.getTeamInfo(idTeam)
        }
        view.team_info_refresh.refresh()

        presenter.getTeamInfo(idTeam)
        return view
    }

    override fun showTeamInfo(team: Team?)
    {
        team?.let {
            this.view?.team_info_stadium?.text = it.strStadium
            this.view?.team_info_location?.text = it.strStadiumLocation
            this.view?.team_info_description?.text = it.strDescriptionEN

            Glide.with(this)
                .load(it.strStadiumThumb)
                .into(this.view!!.team_info_stadium_thumb)
        }
        this.view?.team_info_refresh?.done()
        this.view?.team_info?.visible()
    }

    override fun showError(message: String?) {
        showToast(mContext, message?:"Error")
    }

    override fun onPause() {
        super.onPause()

        presenter.stopRequest()
    }
}
