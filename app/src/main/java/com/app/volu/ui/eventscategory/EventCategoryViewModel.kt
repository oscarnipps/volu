package com.app.volu.ui.eventscategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.volu.data.Resource
import com.app.volu.data.database.entities.EventCategoryEntity
import com.app.volu.data.repo.eventcategory.EventCategoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EventCategoryViewModel @Inject constructor(
    private val eventCategoryRepo: EventCategoryRepo
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _eventCategoriesResult: MutableLiveData<Resource<List<EventCategoryEntity>>> =
        MutableLiveData()


    fun getEventCategoriesFromApi() {
        _eventCategoriesResult.value = Resource.loading()

        compositeDisposable.add(
            eventCategoryRepo.getEventCategoryFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { eventCategoryList -> onEventCategoryListRetrieved(eventCategoryList) },
                    { error -> handleError(error) }
                )
        )
    }

    private fun handleError(error: Throwable) {
        Timber.e("error : ${error.localizedMessage}")
        _eventCategoriesResult.value = Resource.error(error.message)
    }

    private fun onEventCategoryListRetrieved(eventCategoryListResult: Result<List<EventCategoryEntity>>) {
        eventCategoryListResult.fold(
            { result ->
                Timber.d("event category list : $result")
                _eventCategoriesResult.value = Resource.success(result)
            },
            { error -> _eventCategoriesResult.value = Resource.error(error.message) }
        )
    }

    fun getEventCategoriesResult(): LiveData<Resource<List<EventCategoryEntity>>> {
        return _eventCategoriesResult
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}