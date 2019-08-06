package com.example.footbalschedule

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.footbalschedule.league.LeagueActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    @Rule
    @JvmField var rule = ActivityTestRule(LeagueActivity::class.java)

    @Test
    fun test(){
        Thread.sleep(3000)
        onView(withId(R.id.league_list_recview)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(30))
        onView(withId(R.id.league_list_recview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(14, click()))
        Thread.sleep(2000)
        onView(withId(R.id.last_match_recyclerview)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.last_match_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        Thread.sleep(1000)
        onView(withId(R.id.add_favorite_menu)).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Navigate up")).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Standings")).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Team")).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.search_menu_id)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.search_button)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.search_src_text)).perform(replaceText("bayern"), closeSoftKeyboard())
        Thread.sleep(1000)
        onView(withId(R.id.search_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)
        onView(withContentDescription("Players")).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.add_favorite_menu)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.player_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
        onView(withContentDescription("Navigate up")).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Navigate up")).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Navigate up")).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Navigate up")).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("More options")).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.title)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.favorite_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
        onView(withId(R.id.add_favorite_menu)).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Navigate up")).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.favorite_team_menu)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.favorite_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
        onView(withId(R.id.add_favorite_menu)).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Navigate up")).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("Navigate up")).perform(click())
        Thread.sleep(2000)
    }
}