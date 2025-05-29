package com.example.empyreancodeassessment.features.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.empyreancodeassessment.network.MockAPIService
import com.example.empyreancodeassessment.network.models.LoginRequest
import com.example.empyreancodeassessment.network.models.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val mockAPIService: MockAPIService
) : ViewModel() {
    private val _loginSuccess = MutableLiveData<LoginResponse>()
    val loginSuccess: MutableLiveData<LoginResponse> = _loginSuccess

    private val _loginError = MutableLiveData<String>()
    val loginError: MutableLiveData<String> = _loginError

    private val compositeDisposable = CompositeDisposable()

    fun loginUser(username: String, password: String) {
        val loginDisposable = mockAPIService
            .postLogin(LoginRequest(username, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _loginSuccess.value = it },
                { _loginError.value = it.message ?: "Unknown Error" }
            )

        compositeDisposable.add(loginDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}