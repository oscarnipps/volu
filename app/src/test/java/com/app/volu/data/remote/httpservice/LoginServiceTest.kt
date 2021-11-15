package com.app.volu.data.remote.httpservice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.volu.ApiServiceGeneratorUtil
import com.app.volu.LiveDataTestUtil
import com.app.volu.MockResponseFileReader
import com.app.volu.data.remote.request.UserLoginRequest
import com.app.volu.data.repo.auth.AuthRepo
import com.app.volu.data.repo.auth.AuthRepoImpl
import com.app.volu.ui.authentication.LoginViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
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

    private lateinit var authRepo: AuthRepo


    private var responsePath = "api-responses/auth"

    private lateinit var userLoginDetails: UserLoginRequest

    private lateinit var authService: AuthService

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        mockWebServer.start(8000)

        authService = ApiServiceGeneratorUtil.getService(AuthService::class.java)

        userLoginDetails = UserLoginRequest("oscar@yahoo.com", "mandalorian")

        authRepo = AuthRepoImpl(authService)

        loginViewModel = LoginViewModel(authRepo)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }


    @Test
    fun `login api success parses successfully`() {
        val responseBody =
            MockResponseFileReader.getStringFromFile("$responsePath/login-success.json")

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        loginViewModel.loginUser(userLoginDetails)

        val user = LiveDataTestUtil.getOrAwaitValue(loginViewModel.loggedInUser())

        loginViewModel.loggedInUser()


       /* authRepo.loginUser(userLoginDetails)
            .test()
            .assertComplete()
            .assertValueCount(1)*/


        //val result = LiveDataTestUtil.getOrAwaitValue(loginViewModel.loggedInUser())
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }


}