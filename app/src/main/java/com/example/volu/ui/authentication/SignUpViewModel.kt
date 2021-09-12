package com.example.volu.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.volu.R
import com.example.volu.data.Constants
import com.example.volu.data.Resource
import com.example.volu.data.SingleLiveEvent
import com.example.volu.data.ValidationStatus
import com.example.volu.data.repo.auth.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(authRepo: AuthRepo) : ViewModel() {

    private val _authRepo = authRepo
    private val _registrationStatus: MutableLiveData<Resource<String>> = MutableLiveData()
    private val _validationStatus: SingleLiveEvent<Map<ValidationStatus, Int>> = SingleLiveEvent()
    private val _validationStatusMap = mutableMapOf<ValidationStatus, Int>()


    fun registrationStatus(): LiveData<Resource<String>> {
        return this._registrationStatus
    }

    fun validationStatus(): SingleLiveEvent<Map<ValidationStatus, Int>> {
        return this._validationStatus
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun validateInputs(inputsMap: Map<Int, Any>) {

        if (inputsMap.isEmpty()) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.empty_inputs_error

            _validationStatus.setValue(_validationStatusMap)

            return
        }

        validateFirstName(inputsMap[R.string.first_name])

        validateLastName(inputsMap[R.string.last_name])

        validateSex(inputsMap[R.string.sex])

        validatePhoneNumber(inputsMap[R.string.phone_number])

        validateEmail(inputsMap[R.string.email])

        validatePasswords(inputsMap[R.string.password], inputsMap[R.string.confirm_password])

        setValidationStatus()
    }

    private fun setValidationStatus() {
        if (_validationStatusMap.containsKey(ValidationStatus.ERROR)) {

            _validationStatus.setValue(_validationStatusMap)

            return
        }

        _validationStatusMap.clear()

        _validationStatusMap[ValidationStatus.SUCCESS] = R.string.inputs_validation_success

        _validationStatus.setValue(_validationStatusMap)
    }

    private fun validatePasswords(passwordValue: Any?, confirmPasswordValue: Any?) {
        val password = passwordValue as String

        val confirmPassword = confirmPasswordValue as String

        if (password.isEmpty()) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.password_input_error
            return
        }

        if (confirmPassword.isEmpty()) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.confirm_password_input_error
            return
        }

        if (password != confirmPassword) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.password_mismatch_error
        }
    }

    private fun validateEmail(value: Any?) {
        val email = value as String

        Timber.d("email : %s ", email)

        if (email.isEmpty()) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.email_input_error
            return
        }


        if (!Pattern.matches(Constants.EMAIL_PATTERN, email)) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.invalid_email_input_error
        }
    }

    private fun validatePhoneNumber(value: Any?) {
        val phone = value as String

        Timber.d("phone : %s ", phone)

        if (phone.isEmpty()) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.phone_input_error

        }
    }

    private fun validateSex(value: Any?) {
        val gender = value as String

        Timber.d("selected gender : %s ", gender)

        if (gender.isEmpty()) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.sex_input_error
        }
    }

    private fun validateFirstName(value: Any?) {
        val firstName = value as String

        Timber.d("first name is : %s ", firstName)

        if (firstName.isEmpty()) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.first_name_input_error
        }
    }

    private fun validateLastName(value: Any?) {
        val lastName = value as String

        Timber.d("last name is : %s ", lastName)

        if (lastName.isEmpty()) {
            _validationStatusMap[ValidationStatus.ERROR] = R.string.last_name_input_error
        }
    }
}