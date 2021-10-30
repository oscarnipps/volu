package com.example.volu.data.repo.auth

import com.example.volu.data.database.PrefManager
import com.example.volu.data.remote.httpservice.AuthService
import com.example.volu.data.remote.request.UserLoginRequest
import com.example.volu.data.remote.response.UserLoginResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authService: AuthService,
    private val prefManager: PrefManager
) : AuthRepo {


    override fun loginUser(userLoginDetails: UserLoginRequest): Single<Response<UserLoginResponse>> {
        return authService.login(userLoginDetails)
    }

    override fun registerUser() {
        authService.register()
    }
}