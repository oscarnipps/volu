package com.app.volu.data.remote.httpservice

import com.app.volu.data.remote.request.UserLoginRequest
import com.app.volu.data.remote.response.Data
import com.app.volu.data.remote.response.UserLoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @GET("user/register")
    fun register()

    @POST("user/login")
    fun login(@Body userLoginDetails: UserLoginRequest): Single<Response<Data<UserLoginResponse>>>
}