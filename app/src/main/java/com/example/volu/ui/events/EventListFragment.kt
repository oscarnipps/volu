package com.example.volu.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.volu.R
import com.example.volu.databinding.FragmentEventListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventListFragment : Fragment() {

    private lateinit var binding : FragmentEventListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)

        binding.eventFilter.setOnClickListener{
            findNavController().navigate(R.id.navigate_to_event_details)
        }

        return binding.root
    }

}