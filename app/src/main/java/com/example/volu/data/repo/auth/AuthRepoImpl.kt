package com.example.volu.data.repo.auth

import com.example.volu.data.remote.AuthService
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(authService: AuthService) : AuthRepo {

    private var _authService = authService

    override fun loginUser() {
        _authService.login()
    }

    override fun registerUser() {
        _authService.register()
    }
}