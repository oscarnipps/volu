package com.example.volu.ui.authentication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.volu.LiveDataTestUtil
import com.example.volu.data.repo.auth.AuthRepo
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var authRepo: AuthRepo

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(authRepo)
    }

    @Test
    fun `valid email with non-empty password validates successfully`() {
        val email = "oscar@gmail.com"

        val password = "johnsnow"

        val expectedResult = true

        loginViewModel.validateLoginInputs(email, password)

        val actualResult = LiveDataTestUtil.getOrAwaitValue(loginViewModel.isValidInputs())!!

        assertTrue(expectedResult == actualResult)
    }

    @Test
    fun `invalid email with empty password fails validation`() {
        val email = "oscar@gmailcom"

        val password = ""

        val expectedResult = false

        loginViewModel.validateLoginInputs(email, password)

        val actualResult = LiveDataTestUtil.getOrAwaitValue(loginViewModel.isValidInputs())!!

        assertTrue(expectedResult == actualResult)
    }

    @Test
    fun `valid email with empty password fails validation`() {
        val email = "oscar@gmail.com"

        val password = ""

        val expectedResult = false

        loginViewModel.validateLoginInputs(email, password)

        val actualResult = LiveDataTestUtil.getOrAwaitValue(loginViewModel.isValidInputs())!!

        assertTrue(expectedResult == actualResult)
    }

    @Test
    fun `invalid email with non empty password fails validation`() {
        val email = "oscar@gmailcom"

        val password = "johnsnow"

        val expectedResult = false

        loginViewModel.validateLoginInputs(email, password)

        val actualResult = LiveDataTestUtil.getOrAwaitValue(loginViewModel.isValidInputs())!!

        assertTrue(expectedResult == actualResult)
    }

}