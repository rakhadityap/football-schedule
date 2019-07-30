package com.example.footbalschedule.teamdetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.footbalschedule.player.PlayerFragment
import com.example.footbalschedule.teaminfo.TeamInfoFragment

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    val title = listOf("Info", "Players")
    val pages: List<Fragment> = listOf(
        TeamInfoFragment(),
        PlayerFragment()
    )
    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? = title[position]
}