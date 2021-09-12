package com.example.volu.data.remote

import com.example.volu.data.Constants
import retrofit2.http.GET

interface AuthService {

    @GET(Constants.ENDPOINT_REGISTER)
    fun register()

    @GET(Constants.ENDPOINT_LOGIN)
    fun login()
}