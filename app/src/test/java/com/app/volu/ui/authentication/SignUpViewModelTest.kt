package com.app.volu.ui.authentication

import androidx.annotation.StringRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.volu.LiveDataTestUtil
import com.app.volu.R
import com.app.volu.data.repo.auth.AuthRepo
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
        inputsMap[R.id.first_name] = "oscar"

        inputsMap[R.id.last_name] = "ekesiobi"

        inputsMap[R.id.sex] = "male"

        inputsMap[R.id.email] = "oscar@gmail.com"

        inputsMap[R.id.phone] = "09090356771"

        inputsMap[R.id.password] = "12345"

        inputsMap[R.id.confirm_password] = "12345"
    }

    @Test
    fun valid_inputs_show_success_message() {
        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        Assert.assertEquals(validationInputMap?.size,0 )
    }

    @Test
    fun invalid_first_name_show_validation_error_message() {
        inputsMap[R.id.first_name] = ""

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.first_name_input_error

        @StringRes val actualMessageResId = validationInputMap?.get(R.id.first_name)

        Assert.assertEquals(validationInputMap?.size,1 )

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    fun invalid_last_name_show_validation_error_message() {
        inputsMap.replace(R.id.last_name, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.last_name_input_error

        @StringRes val actualMessageResId = validationInputMap!![R.id.last_name]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_sex_input_show_validation_error_message() {
        inputsMap.replace(R.id.sex, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.sex_input_error

        @StringRes val actualMessageResId = validationInputMap!![R.id.sex]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_phone_number_show_validation_error_message() {
        inputsMap.replace(R.id.phone, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.phone_input_error

        @StringRes val actualMessageResId = validationInputMap!![R.id.phone]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_email_show_validation_error_message() {
        inputsMap.replace(R.id.email, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.email_input_error

        @StringRes val actualMessageResId = validationInputMap!![R.id.email]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_password_show_validation_error_message() {
        inputsMap.replace(R.id.password, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.password_input_error

        @StringRes val actualMessageResId = validationInputMap!![R.id.password]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }

    @Test
    @Throws(InterruptedException::class)
    fun invalid_confirm_password_show_validation_error_message() {
        inputsMap.replace(R.id.confirm_password, "")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.confirm_password_input_error

        @StringRes val actualMessageResId = validationInputMap!![R.id.confirm_password]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }


    @Test
    @Throws(InterruptedException::class)
    fun password_mismatch_show_validation_error_message() {
        inputsMap.replace(R.id.password, "1234")

        inputsMap.replace(R.id.confirm_password, "4321")

        signUpViewModel.validateInputs(inputsMap)

        val validationInputMap =
            LiveDataTestUtil.getOrAwaitValue(signUpViewModel.validationStatus())

        @StringRes val expectedMessageResId = R.string.password_mismatch_error

        @StringRes val actualMessageResId = validationInputMap!![R.id.password]

        Assert.assertEquals(expectedMessageResId, actualMessageResId)
    }
}