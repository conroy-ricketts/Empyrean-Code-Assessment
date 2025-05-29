package com.example.empyreancodeassessment.features.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empyreancodeassessment.AppEngine
import com.example.empyreancodeassessment.network.MockAPIService
import com.example.empyreancodeassessment.network.models.ItemResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val mockAPIService: MockAPIService
) : ViewModel() {
    private val _feedItems = MutableLiveData<List<ItemResponse>>()
    val feedItems: MutableLiveData<List<ItemResponse>> = _feedItems

    private val _fetchFeedItemsError = MutableLiveData<String>()
    val fetchFeedItemsError: MutableLiveData<String> = _fetchFeedItemsError

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchFeedItems()
    }

    private fun fetchFeedItems() {
        val feedItemsDisposable = mockAPIService
            .getItems(AppEngine.getInstance().authToken ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _feedItems.value = it },
                { fetchFeedItemsError.value = it.message ?: "Unknown Error" }
            )

        compositeDisposable.add(feedItemsDisposable)
    }

}