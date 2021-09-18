package com.example.volu.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.volu.R
import com.example.volu.databinding.FragmentExploreEventBinding

class ExploreEventFragment : Fragment() {

    private lateinit var binding : FragmentExploreEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_explore_event, container, false)

        return binding.root
    }

}