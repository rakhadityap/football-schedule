package com.example.footbalschedule.league


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.footbalschedule.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LeagueActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LeagueActivity::class.java)

    @Test
    fun leagueActivityTest() {
        val materialCardView = onView(allOf(childAtPosition(allOf(withId(R.id.league_list_recview),childAtPosition(withId(R.id.league_list_refreshview),0)),4),isDisplayed()))
        materialCardView.perform(click())

        val cardView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.next_match_recyclerview),
                        childAtPosition(
                            withId(R.id.next_match_group),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val tabView = onView(
            allOf(
                withContentDescription("Standings"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.league_detail_tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val tabView2 = onView(
            allOf(
                withContentDescription("Team"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.league_detail_tabs),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        tabView2.perform(click())

        val cardView2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.team_recyclerview),
                        childAtPosition(
                            withId(R.id.team_refresh),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        cardView2.perform(click())

        val tabView3 = onView(
            allOf(
                withContentDescription("Players"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.team_detail_tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView3.perform(click())

        val cardView3 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.player_recyclerview),
                        childAtPosition(
                            withId(R.id.player_refresh),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        cardView3.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.player_detail_toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.CollapsingToolbarLayout")),
                            3
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val actionMenuItemView = onView(
            allOf(
                withId(R.id.add_favorite_menu), withContentDescription("Favorites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.team_detail_toolbar),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val appCompatImageButton3 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.team_detail_toolbar),
                        childAtPosition(
                            withId(R.id.team_detail_collapsetoolbar),
                            3
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton3.perform(click())

        val appCompatImageButton4 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.league_detail_toolbar),
                        childAtPosition(
                            withId(R.id.league_detail_collapsetoolbar),
                            3
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton4.perform(click())

        val overflowMenuButton = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        overflowMenuButton.perform(click())

        val appCompatTextView = onView(
            allOf(
                withId(R.id.title), withText("Favorites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.favorite_team_menu), withContentDescription("Team"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.favorite_bottombar),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val cardView4 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.favorite_recyclerview),
                        childAtPosition(
                            withId(R.id.favorite_refresh),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView4.perform(click())

        val actionMenuItemView2 = onView(
            allOf(
                withId(R.id.add_favorite_menu), withContentDescription("Favorites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.team_detail_toolbar),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView2.perform(click())

        pressBack()

        pressBack()
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
