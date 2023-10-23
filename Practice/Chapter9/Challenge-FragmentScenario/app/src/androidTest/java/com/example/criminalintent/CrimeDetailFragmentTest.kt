package com.example.criminalintent

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.Companion.launch
import androidx.fragment.app.testing.launchFragmentInContainer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

//@Ignore("All tests in this class are temporarily disabled")
@RunWith(AndroidJUnit4::class)
class CrimeDetailFragmentTest {

    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setUp() {
        scenario = launch(CrimeDetailFragment::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun editTitle_updatesCrimeTitle() {
        //need to launch fragment in container first
        val scenario = launchFragmentInContainer<CrimeDetailFragment>()
        onView(withId(R.id.crime_title)).perform(ViewActions.typeText("New Crime Title"))

        var actualTitle = ""
        scenario.onFragment { fragment ->
            // Access the fragment's state
            actualTitle = fragment.crime.title
        }

        // Make the assertion outside the onFragment block
        assertEquals("New Crime Title", actualTitle)
    }

    @Test
    fun checkSolved_updatesCrimeIsSolved() {
        val scenario = launchFragmentInContainer<CrimeDetailFragment>()
        onView(withId(R.id.crime_solved)).perform(click())

        // Assume that the initial state of isSolved is false
        var isSolvedInitially = false
        scenario.onFragment { fragment ->
            isSolvedInitially = fragment.crime.isSolved
        }
        assertTrue(isSolvedInitially)
    }
}