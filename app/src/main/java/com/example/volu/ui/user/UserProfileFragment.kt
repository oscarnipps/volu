package com.example.volu.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.volu.R
import com.example.volu.databinding.FragmentUserProfileBinding

class UserProfileFragment :  Fragment() {

    private lateinit var binding : FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false)

        return binding.root
    }


}