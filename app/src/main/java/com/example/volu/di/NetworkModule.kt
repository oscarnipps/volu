package com.example.volu.di

import android.content.Context
import com.example.volu.BuildConfig
import com.example.volu.data.Constants
import com.example.volu.data.remote.interceptors.CacheInterceptor
import com.example.volu.data.remote.interceptors.TokenExpiredInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.API_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttp(
        cache: Cache
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(TokenExpiredInterceptor())

        builder.addInterceptor(CacheInterceptor())

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

        builder.addInterceptor(loggingInterceptor)

        return builder
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun providesCache(@ApplicationContext context: Context): Cache {
        //cache size of 10mb
        val cacheSize = (10 * 1024 * 1024).toLong()

        return Cache(context.cacheDir, cacheSize)
    }

/*
    @Provides
    @Singleton
    @Named("tokenExpiredInterceptor")
    fun provideTokenExpiredInterceptor(): Interceptor {
        return TokenExpiredInterceptor()
    }

    @Provides
    @Singleton
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    @Named("cacheInterceptor")
    fun provideCacheInterceptor(): Interceptor {
        return CacheInterceptor()
    }
*/

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        return GsonConverterFactory.create(gson)
    }
}