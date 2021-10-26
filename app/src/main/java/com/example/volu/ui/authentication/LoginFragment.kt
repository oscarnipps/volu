package com.example.volu.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.volu.R
import com.example.volu.data.Constants
import com.example.volu.data.database.PrefManager
import com.example.volu.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_sign_up)
        }

        binding.password.setOnClickListener {
            retrieveUserPassword()
        }

        binding.login.setOnClickListener {
            loginUser()
        }


        return binding.root
    }

    private fun loginUser() {
        prefManager.saveItem(Constants.IS_LOGGED_IN, true)
        findNavController().navigate(R.id.navigate_to_main)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun retrieveUserPassword() {

    }
}