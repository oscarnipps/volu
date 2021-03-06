package com.app.volu.di

import com.app.volu.data.dao.AuthDao
import com.app.volu.data.database.AppDatabase
import com.app.volu.data.remote.httpservice.AuthService
import com.app.volu.data.repo.auth.AuthRepo
import com.app.volu.data.repo.auth.AuthRepoImpl
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