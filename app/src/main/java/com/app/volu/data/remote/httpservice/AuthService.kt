package com.app.volu.data.remote.httpservice

import com.app.volu.data.remote.request.UserLoginRequest
import com.app.volu.data.remote.request.UserRegistrationRequest
import com.app.volu.data.remote.response.Data
import com.app.volu.data.remote.response.UserLoginResponse
import com.app.volu.data.remote.response.UserRegistrationResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("user/register")
    fun register(@Body userRegistrationRequest: UserRegistrationRequest) : Single<Response<Data<UserRegistrationResponse>>>

    @POST("user/login")
    fun login(@Body userLoginDetails: UserLoginRequest): Single<Response<Data<UserLoginResponse>>>
}