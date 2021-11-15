package com.app.volu.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class TokenExpiredInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)

        if (response.code == 401) {
            //todo: re-authenticate or log user out

            Timber.d("token expired")

        }

        return response
    }

}