package com.app.volu

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestServiceGenerator {

    companion object {

        fun <T> getService(serviceClass: Class<T>, url: HttpUrl): T {
            return Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(getGsonConverterFactory())
                .build()
                .create(serviceClass)
        }

        private fun getGsonConverterFactory(): GsonConverterFactory {
            val gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()

            return GsonConverterFactory.create(gson)
        }
    }

}