package com.app.volu.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.volu.R
import com.app.volu.databinding.LayoutEventFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EventFilterFragment : BottomSheetDialogFragment() {

    lateinit var binding: LayoutEventFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_event_filter, container, false)



        return binding.root
    }
}