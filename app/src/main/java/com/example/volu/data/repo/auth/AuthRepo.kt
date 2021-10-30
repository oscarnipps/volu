package com.example.volu.data.repo.auth

import com.example.volu.data.remote.request.UserLoginRequest
import com.example.volu.data.remote.response.UserLoginResponse
import io.reactivex.Single
import retrofit2.Response

interface AuthRepo {

    fun loginUser(userLoginDetails: UserLoginRequest) : Single<Response<UserLoginResponse>>

    fun registerUser()
}