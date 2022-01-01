package com.app.volu.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.volu.R
import com.app.volu.data.Constants
import com.app.volu.data.Resource
import com.app.volu.data.SingleLiveEvent
import com.app.volu.data.remote.request.UserRegistrationRequest
import com.app.volu.data.remote.response.Data
import com.app.volu.data.remote.response.UserRegistrationResponse
import com.app.volu.data.repo.auth.AuthRepo
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepo: AuthRepo) : ViewModel() {

    private val _registrationStatus: MutableLiveData<Resource<UserRegistrationResponse>> = MutableLiveData()
    private val _validationResults: SingleLiveEvent<Map<Int, Int>> = SingleLiveEvent()
    private val _validationResultMap = mutableMapOf<Int, Int>()
    private var _compositeDisposable = CompositeDisposable()


    fun registrationStatus(): LiveData<Resource<UserRegistrationResponse>> {
        return this._registrationStatus
    }

    fun validationStatus(): SingleLiveEvent<Map<Int, Int>> {
        return this._validationResults
    }


    fun validateInputs(inputsMap: Map<Int, Any>) {

        if (_validationResultMap.isNotEmpty()) {
            _validationResultMap.clear()
        }

        validateFirstName(inputsMap[R.id.first_name])

        validateLastName(inputsMap[R.id.last_name])

        validateSex(inputsMap[R.id.sex])

        validatePhoneNumber(inputsMap[R.id.phone])

        validateEmail(inputsMap[R.id.email])

        validatePasswords(inputsMap[R.id.password], inputsMap[R.id.confirm_password])

        setValidationStatus()
    }


    private fun setValidationStatus() {
        Timber.d("validation result size ${_validationResultMap.size}")
        _validationResults.value = _validationResultMap
    }

    private fun validatePasswords(passwordValue: Any?, confirmPasswordValue: Any?) {
        val password = passwordValue as String

        val confirmPassword = confirmPasswordValue as String

        Timber.d("password : %s ", password)

        Timber.d("confirm password : %s ", confirmPassword)

        if (password.isEmpty()) {
            _validationResultMap[R.id.password] = R.string.password_input_error
            return
        }

        if (confirmPassword.isEmpty()) {
            _validationResultMap[R.id.confirm_password] = R.string.confirm_password_input_error
            return
        }

        if (password != confirmPassword) {
            _validationResultMap[R.id.password] = R.string.password_mismatch_error
            _validationResultMap[R.id.confirm_password] = R.string.password_mismatch_error
        }
    }

    private fun validateEmail(value: Any?) {
        val email = value as String

        Timber.d("email : %s ", email)

        if (email.isEmpty()) {
            _validationResultMap[R.id.email] = R.string.email_input_error
            return
        }

        if (!Pattern.matches(Constants.EMAIL_PATTERN, email)) {
            _validationResultMap[R.id.email] = R.string.invalid_email_input_error
            return
        }
    }

    private fun validatePhoneNumber(value: Any?) {
        val phone = value as String

        Timber.d("phone : %s ", phone)

        if (phone.isEmpty()) {
            _validationResultMap[R.id.phone] = R.string.phone_input_error

        }
    }

    private fun validateSex(value: Any?) {
        val gender = value as String

        Timber.d("selected gender : %s ", gender)

        if (gender.isEmpty()) {
            _validationResultMap[R.id.sex] = R.string.sex_input_error
        }
    }

    private fun validateFirstName(value: Any?) {
        val firstName = value as String

        Timber.d("first name is : %s ", firstName)

        if (firstName.isEmpty()) {
            _validationResultMap[R.id.first_name] = R.string.first_name_input_error
        }
    }

    private fun validateLastName(value: Any?) {
        val lastName = value as String

        Timber.d("last name is : %s ", lastName)

        if (lastName.isEmpty()) {
            _validationResultMap[R.id.last_name] = R.string.last_name_input_error
        }
    }

    fun registerUser(userRegistrationDetails: UserRegistrationRequest) {
        _registrationStatus.value = Resource.loading()

        _compositeDisposable.add(
            authRepo.registerUser(userRegistrationDetails)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {registrationResponse -> handleUserRegistrationResponse(registrationResponse)},
                    {error -> handleUserRegistrationError(error)}
                )
        )
    }

    private fun handleUserRegistrationError(error: Throwable) {
        //todo: check for the type of exception and use user-friendly message

        Timber.d("registration error : ${error.localizedMessage}")

        _registrationStatus.value = Resource.error(error.localizedMessage)
    }

    private fun handleUserRegistrationResponse(response: Response<Data<UserRegistrationResponse>>) {
        if (response.isSuccessful) {
            val data = response.body()?.data as UserRegistrationResponse

            _registrationStatus.value = Resource.success(data)

            return
        }

        val gson = GsonBuilder().create()

        val data = gson.fromJson(response.errorBody()?.charStream(), Data::class.java)

        Timber.d("registration error message : ${data.message}")

        Timber.d("registration error status : ${data.status}")

        _registrationStatus.value = Resource.error(data.message)

    }


    override fun onCleared() {
        super.onCleared()
        _compositeDisposable.clear()

    }
}