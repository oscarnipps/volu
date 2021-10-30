package com.example.volu.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.volu.data.Constants
import com.example.volu.data.Resource
import com.example.volu.data.SingleLiveEvent
import com.example.volu.data.remote.response.UserLoginResponse
import com.example.volu.data.repo.auth.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    authRepo: AuthRepo
) : ViewModel() {

    private var _loggedInUser = MutableLiveData<Resource<UserLoginResponse>>()
    private var _isValidInputs : SingleLiveEvent<Boolean> = SingleLiveEvent()

    //valid if email is valid email and password is not empty
    //invalid if email is not valid / empty or if password is empty
    fun validateLoginInputs(email: String , password : String) {

        if (isValidEmail(email) && isValidPassword(password)) {
            _isValidInputs.value = true
            return
        }

        _isValidInputs.value = false
    }

    fun loginUser() {
    }

    private fun isValidEmail(value: Any?) : Boolean {
        val email = value as String

        Timber.d("email : %s ", email)

        if (email.isEmpty() || !Pattern.matches(Constants.EMAIL_PATTERN, email)) {
            return false
        }

        return true
    }

    private fun isValidPassword(value: Any?) : Boolean {
        val password = value as String

        Timber.d("password : %s ", password)

        return password.isNotEmpty()
    }

    fun isValidInputs() : SingleLiveEvent<Boolean> {
        return _isValidInputs
    }

    fun loggedInUser(): LiveData<Resource<UserLoginResponse>> {
        return _loggedInUser
    }

}