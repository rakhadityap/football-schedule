package com.example.footbalschedule.leaguedetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footbalschedule.R
import com.example.footbalschedule.app.showToast

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment()
{
    val leagueId by lazy {
        activity?.intent?.getStringExtra("leagueId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_match, container, false)

        showToast(activity?.applicationContext!!, leagueId.toString())

        return view
    }


}
