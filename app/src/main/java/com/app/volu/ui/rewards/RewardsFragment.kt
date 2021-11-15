package com.app.volu.ui.rewards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.app.volu.R
import com.app.volu.databinding.FragmentRewardsBinding

class RewardsFragment : Fragment() {

    private lateinit var binding : FragmentRewardsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rewards, container, false)

        return binding.root
    }

}