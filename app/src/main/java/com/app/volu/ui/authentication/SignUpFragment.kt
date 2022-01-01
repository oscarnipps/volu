package com.app.volu.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.volu.R
import com.app.volu.data.Constants
import com.app.volu.data.Resource
import com.app.volu.data.Resource.Status.*
import com.app.volu.data.database.PrefManager
import com.app.volu.data.remote.request.UserRegistrationRequest
import com.app.volu.data.remote.response.UserRegistrationResponse
import com.app.volu.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    @Inject
    lateinit var prefManager: PrefManager

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        binding.registerButton.setOnClickListener {
            //findNavController().navigate(R.id.navigate_to_event_category)
            viewModel.validateInputs(getUserInputs())
        }

        binding.profileImage.setOnClickListener { showSelectProfileImageDialog() }

        binding.title.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun showSelectProfileImageDialog() {
        //todo: show dialog
    }

    private fun getUserInputs(): Map<Int, Any> {
        val userInputsMap = mutableMapOf<Int, Any>()

        userInputsMap[R.id.first_name] = binding.userInfoLayout.firstName.text.toString().trim()

        userInputsMap[R.id.last_name] = binding.userInfoLayout.lastName.text.toString().trim()

        userInputsMap[R.id.sex] = binding.userInfoLayout.sex.text.toString().trim()

        userInputsMap[R.id.email] = binding.userInfoLayout.email.text.toString().trim()

        userInputsMap[R.id.phone] = binding.userInfoLayout.phone.text.toString().trim()

        userInputsMap[R.id.password] = binding.userInfoLayout.password.text.toString().trim()

        userInputsMap[R.id.confirm_password] =
            binding.userInfoLayout.confirmPassword.text.toString().trim()

        return userInputsMap
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpGenderInputDropDown()

        viewModel.validationStatus().observe(viewLifecycleOwner, valStatusObserver())

        viewModel.registrationStatus().observe(viewLifecycleOwner, registrationStatusObserver())

    }

    private fun registrationStatusObserver(): Observer<Resource<UserRegistrationResponse>> {
        return Observer<Resource<UserRegistrationResponse>> { result ->

            when (result.status) {
                LOADING -> {
                    //todo: show loading dialog with progress bar
                    binding.loginProgress.visibility = View.VISIBLE
                    binding.imageContainer.visibility = View.GONE
                    binding.registerButton.visibility = View.GONE
                    binding.userInfoLayout.inputContainers.visibility = View.GONE

                }

                ERROR -> {
                    //todo: hide progress bar and show error dialog
                    binding.loginProgress.visibility = View.GONE
                    binding.imageContainer.visibility = View.VISIBLE
                    binding.registerButton.visibility = View.VISIBLE
                    binding.userInfoLayout.inputContainers.visibility = View.VISIBLE
                }

                SUCCESS -> {
                    binding.loginProgress.visibility = View.GONE
                    binding.imageContainer.visibility = View.VISIBLE
                    binding.registerButton.visibility = View.VISIBLE
                    binding.userInfoLayout.inputContainers.visibility = View.VISIBLE

                    Toast.makeText(requireContext(),getString(R.string.sign_up_success),Toast.LENGTH_SHORT).show()

                    prefManager.saveItem(Constants.IS_LOGGED_IN, true)

                    prefManager.saveItem(Constants.ACCESS_TOKEN, result.data?.accessToken)

                    findNavController().navigate(R.id.navigate_to_event_category)

                }
            }
        }
    }

    private fun valStatusObserver(): Observer<Map<Int, Int>> {
        return Observer<Map<Int, Int>> { result ->

            Timber.d("validation result size ${result.size}")

            if (result.isNotEmpty()) {

                displayValidationError(result)

                return@Observer
            }

            Timber.d("input validation successful")

            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()

            viewModel.registerUser(getUserRegistrationDetails())
        }
    }

    private fun getUserRegistrationDetails(): UserRegistrationRequest {

        return UserRegistrationRequest(
            binding.userInfoLayout.email.text.toString().trim(),

            binding.userInfoLayout.firstName.text.toString().trim(),

            binding.userInfoLayout.lastName.text.toString().trim(),

            binding.userInfoLayout.password.text.toString().trim(),

            binding.userInfoLayout.phone.text.toString().trim(),

            binding.userInfoLayout.sex.text.toString().trim()
        )
    }

    private fun displayValidationError(result: Map<Int, Int>) {
        result.forEach {

            when (it.key) {

                R.id.first_name -> binding.userInfoLayout.firstName.error = getString(it.value)

                R.id.last_name -> binding.userInfoLayout.lastName.error = getString(it.value)

                R.id.sex -> binding.userInfoLayout.sex.error = getString(it.value)

                R.id.email -> binding.userInfoLayout.email.error = getString(it.value)

                R.id.phone -> binding.userInfoLayout.phone.error = getString(it.value)

                R.id.password -> binding.userInfoLayout.password.error = getString(it.value)

                R.id.confirm_password -> binding.userInfoLayout.confirmPassword.error =
                    getString(it.value)
            }

        }
    }

    private fun setUpGenderInputDropDown() {
        val genderList = resources.getStringArray(R.array.gender_list)

        val genderAdapter = ArrayAdapter(requireContext(), R.layout.gender_list_item, genderList)

        (binding.userInfoLayout.sexContainer.editText as AutoCompleteTextView)
            .setAdapter(genderAdapter)

    }


}