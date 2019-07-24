package com.example.footbalschedule.leaguedetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.footbalschedule.match.MatchFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)
{
    val title = listOf("Match", "Standings", "Team")
    val pages = listOf(
        MatchFragment(),
            StandingsFragment(),
            TeamFragment()
    )

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? = title[position]

}