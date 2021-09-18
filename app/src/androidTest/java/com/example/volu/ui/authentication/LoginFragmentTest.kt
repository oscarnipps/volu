package com.example.volu.ui.authentication

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.volu.R
import com.example.volu.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginFragmentTest {

    @get: Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun show_log_in_view() {
        launchFragmentInHiltContainer<LoginFragment> { }
    }

    @Test
    fun valid_inputs_logs_in_user() {
        launchFragmentInHiltContainer<LoginFragment> { }

        onView(withId(R.id.email)).perform(typeText("oscarnipps@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.password)).perform(typeText("123456789"), closeSoftKeyboard())

        onView(withId(R.id.login)).perform(click())
    }

    @Test
    fun navigate_to_sign_up_view() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<LoginFragment> {
            navController.setGraph(R.navigation.intro_nav_graph)

            navController.setCurrentDestination(R.id.loginFragment)

            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.sign_up)).perform(click())

        assertTrue(navController.currentDestination!!.id == R.id.signUpFragment)
    }
}