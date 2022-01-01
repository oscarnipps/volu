package com.app.volu.data.remote.httpservice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.volu.LiveDataTestUtil
import com.app.volu.MockResponseFileReader
import com.app.volu.RxJavaSchedulerRule
import com.app.volu.TestServiceGenerator
import com.app.volu.data.Resource
import com.app.volu.data.remote.request.UserLoginRequest
import com.app.volu.data.remote.response.UserLoginResponse
import com.app.volu.data.repo.auth.AuthRepo
import com.app.volu.data.repo.auth.AuthRepoImpl
import com.app.volu.ui.authentication.LoginViewModel
import com.google.common.truth.Truth.assertThat
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginServiceTest {

    private val mockWebServer = MockWebServer()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val rxJavaSchedulerRule = RxJavaSchedulerRule()

    private lateinit var authRepo: AuthRepo

    private var responsePath = "api-responses/auth"

    private lateinit var userLoginDetails: UserLoginRequest

    private lateinit var authService: AuthService

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        mockWebServer.start(8000)

        authService = TestServiceGenerator.getService(
            AuthService::class.java,
            mockWebServer.url("/")
        )

        userLoginDetails = UserLoginRequest("bobby@yahoo.com", "susan99")

        authRepo = AuthRepoImpl(authService)

        loginViewModel = LoginViewModel(authRepo)
    }


    @Test
    fun `user with valid details logs in successfully with retrieved token`() {
        val responseBody = MockResponseFileReader.content("$responsePath/login-success.json")

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        loginViewModel.loginUser(userLoginDetails)

        val result = LiveDataTestUtil.getOrAwaitValue(loginViewModel.loggedInUser())

        assertThat(result?.status).isEqualTo(Resource.Status.SUCCESS)

        val data = result?.data as UserLoginResponse

        assertThat(data.accessToken).isNotEmpty()

        assertThat(data.accessToken).isNotNull()
    }

    @Test
    fun `invalid username or password returns server error message`() {
        val responseBody = MockResponseFileReader.content("$responsePath/login-failure.json")

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(401)
                .setBody(responseBody)
        )

        loginViewModel.loginUser(userLoginDetails)

        val result = LiveDataTestUtil.getOrAwaitValue(loginViewModel.loggedInUser())

        val expectedErrorMessage = "username / password not valid"

        assertThat(result?.status).isEqualTo(Resource.Status.ERROR)

        assertThat(result?.data).isNull()

        assertThat(result?.message).isNotNull()

        assertThat(result?.message).isNotEmpty()

        assertThat(result?.message).isEqualTo(expectedErrorMessage)
    }


    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }


}