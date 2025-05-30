package com.example.empyreancodeassessment.features.itemDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empyreancodeassessment.AppEngine
import com.example.empyreancodeassessment.network.MockAPIService
import com.example.empyreancodeassessment.network.models.CommentResponse
import com.example.empyreancodeassessment.network.models.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ItemDetailViewModel @Inject constructor(
    private val mockAPIService: MockAPIService
) : ViewModel() {
    private val _author = MutableLiveData<UserResponse>()
    val author: MutableLiveData<UserResponse> = _author

    private val _fetchAuthorError = MutableLiveData<String>()
    val fetchAuthorError: MutableLiveData<String> = _fetchAuthorError

    private val _itemComments = MutableLiveData<List<CommentResponse>>()
    val itemComments: MutableLiveData<List<CommentResponse>> = _itemComments

    private val _fetchItemCommentsError = MutableLiveData<String>()
    val fetchItemCommentsError: MutableLiveData<String> = _fetchItemCommentsError

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchAuthor()
        fetchItemComments()
    }

    private fun fetchAuthor() {
        val authorId = AppEngine.getInstance().currentItem?.userId

        if (authorId != null) {
            val authorDisposable = mockAPIService
                .getUser(AppEngine.getInstance().authToken ?: "", authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _author.value = it },
                    { _fetchAuthorError.value = it.message ?: "Unknown Error" }
                )

            compositeDisposable.add(authorDisposable)
        }
    }

    private fun fetchItemComments() {
        val itemId = AppEngine.getInstance().currentItem?.id

        if (itemId != null) {
            val itemCommentsDisposable = mockAPIService
                .getItemComments(AppEngine.getInstance().authToken ?: "", itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _itemComments.value = it },
                    { _fetchItemCommentsError.value = it.message ?: "Unknown Error" }
                )

            compositeDisposable.add(itemCommentsDisposable)
        }
    }
}