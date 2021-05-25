package com.mrtwoza.marvel.app.listComics.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mrtwoza.marvel.R
import com.mrtwoza.marvel.app.listComics.model.Comic
import kotlinx.android.synthetic.main.activity_comics.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComicsActivityTest {

    @get: Rule
    var activityRule: ActivityScenarioRule<ComicsActivity> = ActivityScenarioRule(ComicsActivity::class.java)

    @Test
    fun showLoader() {
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun setRecyclerView() {
        val recyclerView = onView(withId(R.id.recyclerView_of_comics)).check(matches(isDisplayed()))

    }

}