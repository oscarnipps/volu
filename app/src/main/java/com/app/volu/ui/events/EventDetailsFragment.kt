package com.app.volu.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.app.volu.R
import com.app.volu.databinding.FragmentEventDetailBinding

class EventDetailsFragment : Fragment() {

    private lateinit var binding : FragmentEventDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container, false)





        return binding.root
    }
}