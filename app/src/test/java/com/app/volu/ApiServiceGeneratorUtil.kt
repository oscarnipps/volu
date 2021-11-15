package com.app.volu

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceGeneratorUtil {

    companion object {

        fun <T> getService(serviceClass: Class<T>): T {
           return  Retrofit.Builder()
                .baseUrl("http://api-volu.herokuapp.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(serviceClass)
        }
    }

}