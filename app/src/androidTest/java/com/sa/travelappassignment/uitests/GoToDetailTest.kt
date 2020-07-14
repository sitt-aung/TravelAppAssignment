package com.sa.travelappassignment.uitests

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.sa.travelappassignment.R
import com.sa.travelappassignment.activities.MainActivity
import com.sa.travelappassignment.views.viewholders.CountryViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class GoToDetailTest {

    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    open fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun tapOnCountryItem_navigateToCountryDetails() {
        onView(withId(R.id.rvCountry))
                .perform(RecyclerViewActions.actionOnItemAtPosition<CountryViewHolder>(1, click()))

        onView(withId(R.id.tvDetailDescription))
            .check(matches(isDisplayed()))
    }

//    @Test
//    fun tapOnTourItem_navigateToTourDetails() {
//        onView(withId(R.id.rvTour))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<CountryViewHolder>(1, click()))
//
//        onView(withId(R.id.tvDetailDescription))
//            .check(matches(isDisplayed()))
//    }
}