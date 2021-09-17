package com.example.volu.ui.authentication

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.volu.R
import com.example.volu.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SignUpFragmentTest {

    @get: Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    private var inputsMap = mutableMapOf<Int, String>()

    @Before
    fun setUp() {
        hiltAndroidRule.inject()

        MockitoAnnotations.initMocks(this)

        setUpInputs()
    }

    private fun setUpInputs() {
        inputsMap[R.string.first_name] = "oscar"

        inputsMap[R.string.last_name] = "ekesiobi"

        inputsMap[R.string.sex] = "male"

        inputsMap[R.string.email] = "oscar@gmail.com"

        inputsMap[R.string.phone_number] = "09090356771"

        inputsMap[R.string.password] = "12345"

        inputsMap[R.string.confirm_password] = "12345"
    }


    @Test
    fun show_sign_up_fragment() {
        launchFragmentInHiltContainer<SignUpFragment> {
        }

        onView(withId(R.id.profile_image)).check(matches(isDisplayed()))
    }

    @Test
    fun valid_inputs_shows_success_message() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<SignUpFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.first_name)).perform(scrollTo(), typeText(inputsMap[R.string.first_name]))

        onView(withId(R.id.last_name)).perform(scrollTo(), typeText(inputsMap[R.string.last_name]))

        //click to show the dropdown then select an item
        onView(withId(R.id.sex_container)).perform(scrollTo(), click())
        onData(equalTo("Male")).inRoot(RootMatchers.isPlatformPopup()).perform(click())

        onView(withId(R.id.email)).perform(scrollTo(), typeText(inputsMap[R.string.email]))

        onView(withId(R.id.phone)).perform(scrollTo(), typeText(inputsMap[R.string.phone_number]))

        onView(withId(R.id.password)).perform(scrollTo(), typeText(inputsMap[R.string.password]))

        onView(withId(R.id.confirm_password)).perform(scrollTo(), typeText(inputsMap[R.string.confirm_password]))

        onView(withId(R.id.register)).perform(scrollTo(), click())

        Mockito.verify(navController).navigate(R.id.navigate_to_event_category)
    }

    @Test
    fun gender_input_selected_from_dropdown() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<SignUpFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        //click to show the dropdown
        onView(withId(R.id.sex_container)).perform(scrollTo(), click())

        onData(equalTo("Female")).inRoot(RootMatchers.isPlatformPopup()).perform(click())

        onView(withText("Female")).check(matches(isDisplayed()))
    }

}