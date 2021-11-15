package com.app.volu.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.volu.R
import com.app.volu.data.Constants
import com.app.volu.data.Resource
import com.app.volu.data.Resource.*
import com.app.volu.data.Resource.Status.*
import com.app.volu.data.database.PrefManager
import com.app.volu.data.remote.request.UserLoginRequest
import com.app.volu.data.remote.response.UserLoginResponse
import com.app.volu.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var prefManager: PrefManager
    private var email = ""
    private var password = ""
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        loginViewModel.validationInputStatus()
            .observe(viewLifecycleOwner, validationInputStatusObserver())

        loginViewModel.loggedInUser().observe(viewLifecycleOwner, loggedInUserObserver())

        binding.signUp.setOnClickListener { findNavController().navigate(R.id.navigate_to_sign_up) }

        binding.password.setOnClickListener { retrieveUserPassword() }

        binding.login.setOnClickListener {
            email = binding.email.text.toString().trim()

            password = binding.email.text.toString().trim()

            loginViewModel.validateLoginInputs(email, password)
        }

        return binding.root
    }

    private fun validationInputStatusObserver(): Observer<Boolean> {
        return Observer { isValidInput ->

            if (isValidInput) {
                loginViewModel.loginUser(UserLoginRequest(email, password))
                return@Observer
            }

            //todo : show error message dialog
        }
    }

    private fun loggedInUserObserver(): Observer<Resource<UserLoginResponse>> {
        return Observer { result ->

            when (result.status) {
                LOADING -> {
                    //todo: show progress bar
                }

                ERROR -> {
                    //todo: show dialog with error message
                }

                SUCCESS -> {
                    prefManager.saveItem(Constants.IS_LOGGED_IN, true)
                    findNavController().navigate(R.id.navigate_to_main)
                }

            }
        }
    }


    private fun retrieveUserPassword() {
    }
}