package com.example.footbalschedule.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footbalschedule.R
import com.example.footbalschedule.model.Match

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment(), MatchView
{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_match, container, false)

        val presenter = MatchPresenter(this)
        val leagueId: String = activity?.intent?.getStringExtra("leagueId") ?: "0000"

        presenter.getFutureMatches(leagueId)
        presenter.getPastMatches(leagueId)

        return view
    }

    override fun showFutureMatches(matches: List<Match>)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPastMatches(matches: List<Match>)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
