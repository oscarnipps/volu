package com.app.volu.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.volu.R
import com.app.volu.databinding.FragmentExploreEventBinding

class ExploreEventFragment : Fragment() {

    private lateinit var binding : FragmentExploreEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_explore_event, container, false)

        binding.eventFilter.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_event_filter)
        }

        return binding.root
    }

}