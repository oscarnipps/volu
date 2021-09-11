package com.example.volu.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.volu.R
import com.example.volu.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.signUp.setOnClickListener{
            navController.navigate(R.id.navigate_to_sign_up)
        }

        binding.password.setOnClickListener{
            retrieveUserPassword()
        }

        binding.login.setOnClickListener{
            loginUser()
        }


        return binding.root
    }

    private fun loginUser() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
    }

    private fun retrieveUserPassword() {

    }
}