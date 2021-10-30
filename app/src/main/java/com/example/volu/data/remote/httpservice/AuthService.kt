package com.example.volu.data.remote.httpservice

import com.example.volu.data.Constants
import com.example.volu.data.remote.request.UserLoginRequest
import com.example.volu.data.remote.response.UserLoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface AuthService {

    @GET(Constants.ENDPOINT_REGISTER)
    fun register()

    @GET(Constants.ENDPOINT_LOGIN)
    fun login(@Body userLoginDetails : UserLoginRequest) : Single<Response<UserLoginResponse>>
}