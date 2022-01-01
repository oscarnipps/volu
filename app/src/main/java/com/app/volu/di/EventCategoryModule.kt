package com.app.volu.di

import com.app.volu.data.remote.httpservice.EventCategoryService
import com.app.volu.data.repo.eventcategory.EventCategoryRepo
import com.app.volu.data.repo.eventcategory.EventCategoryRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EventCategoryModule {

    @Provides
    @Singleton
    fun providesEventCategoryRepo(eventCategoryRepoImpl: EventCategoryRepoImpl): EventCategoryRepo {
        return eventCategoryRepoImpl
    }

    @Provides
    @Singleton
    fun providesEventCategoryService(retrofit : Retrofit): EventCategoryService {
        return retrofit.create(EventCategoryService::class.java)
    }
}