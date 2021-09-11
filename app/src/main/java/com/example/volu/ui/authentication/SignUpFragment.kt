package com.example.volu.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.volu.R
import com.example.volu.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel : SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        binding.register.setOnClickListener{
            validateUserInputs()
        }

        return binding.root
    }

    private fun validateUserInputs() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()


    }


}