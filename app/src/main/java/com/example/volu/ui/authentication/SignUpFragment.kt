package com.example.volu.ui.authentication

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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.volu.R
import com.example.volu.data.Resource
import com.example.volu.data.Resource.Status.*
import com.example.volu.data.ValidationStatus
import com.example.volu.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        navController = findNavController()

        binding.register.setOnClickListener {
            viewModel.validateInputs(getUserInputs())
        }

        return binding.root
    }

    private fun getUserInputs(): Map<Int, Any> {
        val userInputsMap = mutableMapOf<Int, Any>()

        userInputsMap[R.string.first_name] = binding.userInfoLayout.firstName.text.toString().trim()

        userInputsMap[R.string.last_name] = binding.userInfoLayout.lastName.text.toString().trim()

        userInputsMap[R.string.sex] = binding.userInfoLayout.sex.text.toString().trim()

        userInputsMap[R.string.email] = binding.userInfoLayout.email.text.toString().trim()

        userInputsMap[R.string.phone_number] = binding.userInfoLayout.phone.text.toString().trim()

        userInputsMap[R.string.password] = binding.userInfoLayout.password.text.toString().trim()

        userInputsMap[R.string.confirm_password] =
            binding.userInfoLayout.confirmPassword.text.toString().trim()

        return userInputsMap
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpGenderInputDropDown()

        viewModel.validationStatus().observe(viewLifecycleOwner, valStatusObserver())

        viewModel.registrationStatus().observe(viewLifecycleOwner, registrationStatusObserver())

    }

    private fun registrationStatusObserver(): Observer<Resource<String>> {
        return Observer<Resource<String>> { result ->

            when (result.status) {
                LOADING -> {
                    //todo: show loading progress bar
                }

                ERROR -> {
                    //todo: hide progress bar and show error dialog
                }

                SUCCESS -> {
                    //todo: show success dialog
                }
            }
        }
    }

    private fun valStatusObserver(): Observer<Map<ValidationStatus, Int>> {
        return Observer<Map<ValidationStatus, Int>> { result ->

            if (result.contains(ValidationStatus.SUCCESS)) {

                Timber.d("input validation successful")

                //todo : "api call to register user"

                return@Observer
            }

            val messageResId = result[ValidationStatus.ERROR]

            val message = getString(messageResId!!)

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            Timber.d("validation not successful with message $message")
        }
    }

    private fun setUpGenderInputDropDown() {
        val genderList = resources.getStringArray(R.array.gender_list)

        val genderAdapter = ArrayAdapter(requireContext(), R.layout.gender_list_item, genderList)

        (binding.userInfoLayout.sexContainer.editText as AutoCompleteTextView)
            .setAdapter(genderAdapter)

    }


}