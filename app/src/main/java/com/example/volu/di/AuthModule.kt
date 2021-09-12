package com.example.volu.di

import com.example.volu.data.dao.AuthDao
import com.example.volu.data.local.AppDatabase
import com.example.volu.data.remote.AuthService
import com.example.volu.data.repo.auth.AuthRepo
import com.example.volu.data.repo.auth.AuthRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun providesAuthDao(database: AppDatabase) : AuthDao {
        return database.authDao()
    }


    @Provides
    @Singleton
    fun providesAuthRepo(authRepoImpl: AuthRepoImpl): AuthRepo {
        return authRepoImpl
    }


    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

}