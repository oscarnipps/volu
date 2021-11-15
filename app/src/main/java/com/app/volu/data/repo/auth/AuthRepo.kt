package com.app.volu.data.repo.auth

import com.app.volu.data.remote.request.UserLoginRequest
import com.app.volu.data.remote.response.Data
import com.app.volu.data.remote.response.UserLoginResponse
import io.reactivex.Single
import retrofit2.Response

interface AuthRepo {

    fun loginUser(userLoginDetails: UserLoginRequest) : Single<Response<Data<UserLoginResponse>>>

    fun registerUser()
}