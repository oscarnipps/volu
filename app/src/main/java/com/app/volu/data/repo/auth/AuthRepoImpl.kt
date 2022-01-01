package com.app.volu.data.repo.auth

import com.app.volu.data.remote.httpservice.AuthService
import com.app.volu.data.remote.request.UserLoginRequest
import com.app.volu.data.remote.request.UserRegistrationRequest
import com.app.volu.data.remote.response.Data
import com.app.volu.data.remote.response.UserLoginResponse
import com.app.volu.data.remote.response.UserRegistrationResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepo {


    override fun loginUser(userLoginDetails: UserLoginRequest): Single<Response<Data<UserLoginResponse>>> {
        return authService.login(userLoginDetails)
    }

    override fun registerUser(userRegistrationDetails: UserRegistrationRequest): Single<Response<Data<UserRegistrationResponse>>> {
        return authService.register(userRegistrationDetails)

    }

}