package com.app.volu.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        //todo: check if there is network and return from cache when there is no network
        return chain.proceed(request)
    }

}