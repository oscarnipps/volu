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
import androidx.navigation.fragment.findNavController
import com.example.volu.R
import com.example.volu.data.Resource
import com.example.volu.data.Resource.Status.*
import com.example.volu.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        binding.register.setOnClickListener {
            //findNavController().navigate(R.id.navigate_to_event_category)
            viewModel.validateInputs(getUserInputs())
        }

        binding.title.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
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

    private fun valStatusObserver(): Observer<Map<Int, Int>> {
        return Observer<Map<Int, Int>> { result ->

            Timber.d("validation result size ${result.size}")

            if (result.isNotEmpty()) {

                displayValidationError(result)

                return@Observer
            }

            Timber.d("input validation successful")

            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()

            //todo : "api call to register user"

            findNavController().navigate(R.id.navigate_to_event_category)

        }
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