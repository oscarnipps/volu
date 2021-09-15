package com.example.volu.ui.authentication

import androidx.annotation.StringRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.volu.LiveDataTestUtil
import com.example.volu.R
import com.example.volu.data.ValidationStatus
import com.example.volu.data.repo.auth.AuthRepo
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignUpViewModelTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var authRepo: AuthRepo

    private lateinit var signUpViewModel: SignUpViewModel

    private var inputsMap = mutableMapOf<Int, Any>()

    @Before
    fun setUp() {
        signUpViewModel = SignUpViewModel(authRepo)
        setUpInputs()
    }

    @After
    fun cleanUp() {
        inputsMap.clear()
    }

    private fun setUpInputs() {
        inputsMap[R.string.first_name] = "oscar"

        inputsMap[R.string.last_name] = "ekesiobi"

        inputsMap[R.string.sex] = "male"

        inputsMap[R.string.email] = "oscar@gmail.com"

        inputsMap[R.string.phone_number] = "09090356771"

        inputsMap[R.string.password] = "12345"

        inputsMap[R.string.confirm_password] = "12345"
    }

    @Test
    fun empty_inputs_show_validation_error_message() {
        inputsMap.clear()

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.empty_inputs_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    fun invalid_first_name_show_validation_error_message() {
        inputsMap[R.string.first_name] = ""

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.first_name_input_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    fun invalid_last_name_show_validation_error_message() {
        inputsMap.replace(R.string.last_name, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.last_name_input_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_sex_input_show_validation_error_message() {
        inputsMap.replace(R.string.sex, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.sex_input_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_phone_number_show_validation_error_message() {
        inputsMap.replace(R.string.phone_number, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.phone_input_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_email_show_validation_error_message() {
        inputsMap.replace(R.string.email, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.email_input_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_password_show_validation_error_message() {
        inputsMap.replace(R.string.password, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.password_input_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_confirm_password_show_validation_error_message() {
        inputsMap.replace(R.string.confirm_password, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.confirm_password_input_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }


    @Test
    @Throws(InterruptedException::class)
    fun password_mismatch_show_validation_error_message() {
        inputsMap.replace(R.string.password, "1234")

        inputsMap.replace(R.string.confirm_password, "4321")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.password_mismatch_error

        @StringRes val actualMessageResId = validationInputMap!![ValidationStatus.ERROR]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }
}