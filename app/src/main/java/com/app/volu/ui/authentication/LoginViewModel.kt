package com.app.volu.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.volu.data.Constants
import com.app.volu.data.Resource
import com.app.volu.data.SingleLiveEvent
import com.app.volu.data.remote.request.UserLoginRequest
import com.app.volu.data.remote.response.Data
import com.app.volu.data.remote.response.UserLoginResponse
import com.app.volu.data.repo.auth.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    private var _loggedInUser = MutableLiveData<Resource<UserLoginResponse>>()
    private var _compositeDisposable = CompositeDisposable()
    private var _isValidInputs: SingleLiveEvent<Boolean> = SingleLiveEvent()


    fun validateLoginInputs(email: String, password: String) {
        if (isValidEmail(email) && isValidPassword(password)) {
            _isValidInputs.value = true
            return
        }

        _isValidInputs.value = false
    }

    private fun isValidEmail(value: Any?): Boolean {
        val email = value as String

        Timber.d("email : %s ", email)

        if (email.isEmpty() || !Pattern.matches(Constants.EMAIL_PATTERN, email)) {
            return false
        }

        return true
    }

    private fun isValidPassword(value: Any?): Boolean {
        val password = value as String

        Timber.d("password : %s ", password)

        return password.isNotEmpty()
    }

    fun validationInputStatus(): SingleLiveEvent<Boolean> {
        return _isValidInputs
    }

    fun loginUser(userLoginDetails: UserLoginRequest) {
        _loggedInUser.value = Resource.loading(null)

        _compositeDisposable.add(
            authRepo
                .loginUser(userLoginDetails)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response -> handleResponse(response) },
                    { error -> handleError(error) }
                )
        )
    }

    private fun handleError(error: Throwable) {
        //todo: check for the type of exception and use user-friendly message

        Timber.d("login error : ${error.localizedMessage}")

        _loggedInUser.value = Resource.error(error.localizedMessage)
    }

    private fun handleResponse(response: Response<Data<UserLoginResponse>>) {
        if (response.code() == 200) {

            if (response.body() != null) {

                val userLoginResponse = response.body()?.data as UserLoginResponse

                _loggedInUser.value = Resource.success(userLoginResponse)

                return
            }
        }

        _loggedInUser.value = Resource.error(response.message())

    }

    fun loggedInUser(): LiveData<Resource<UserLoginResponse>> {
        return _loggedInUser
    }

    override fun onCleared() {
        super.onCleared()
        _compositeDisposable.clear()
    }

}