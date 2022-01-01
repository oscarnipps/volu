package com.app.volu.data.remote.httpservice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.volu.LiveDataTestUtil
import com.app.volu.MockResponseFileReader
import com.app.volu.RxJavaSchedulerRule
import com.app.volu.TestServiceGenerator
import com.app.volu.data.Resource
import com.app.volu.data.repo.eventcategory.EventCategoryRepo
import com.app.volu.data.repo.eventcategory.EventCategoryRepoImpl
import com.app.volu.ui.eventscategory.EventCategoryViewModel
import com.google.common.truth.Truth.assertThat
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventCategoryServiceTest {

    private val mockWebServer = MockWebServer()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val rxJavaSchedulerRule = RxJavaSchedulerRule()

    private lateinit var eventCategoryRepo: EventCategoryRepo

    private var responsePath = "api-responses/eventcategory"

    private lateinit var eventCategoryService: EventCategoryService

    private lateinit var eventCategoryViewModel: EventCategoryViewModel

    @Before
    fun setUp() {
        mockWebServer.start(8000)

        eventCategoryService = TestServiceGenerator.getService(
            EventCategoryService::class.java,
            mockWebServer.url("/")
        )

        eventCategoryRepo = EventCategoryRepoImpl(eventCategoryService)

        eventCategoryViewModel = EventCategoryViewModel(eventCategoryRepo)
    }

    @Test
    fun `success response gets mapped to event category list items`() {
        val responseBody =
            MockResponseFileReader.content("$responsePath/event-categories-success.json")

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
        )

        eventCategoryViewModel.getEventCategoriesFromApi()

        val result =
            LiveDataTestUtil.getOrAwaitValue(eventCategoryViewModel.getEventCategoriesResult())

        assertThat(result?.status).isEqualTo(Resource.Status.SUCCESS)

        val data = result?.data

        assertThat(data).isNotNull()

        assertThat(data).isNotEmpty()

    }
}