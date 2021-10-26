package com.example.volu.ui.eventscategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.volu.R
import com.example.volu.data.database.entities.EventCategoryEntity
import com.example.volu.databinding.FragmentEventCategoryBinding

class EventCategoryFragment : Fragment(), EventCategoryAdapter.EventCategoryInterface {

    private lateinit var binding: FragmentEventCategoryBinding
    private lateinit var eventCategoryAdapter: EventCategoryAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_event_category, container, false)

        setUpRecyclerView()

        binding.done.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_main)
        }

        return binding.root
    }

    private fun setUpRecyclerView() {
        eventCategoryAdapter = EventCategoryAdapter(getItems())

        recyclerView = binding.categoryRecyclerView

        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)

            adapter = eventCategoryAdapter
        }
    }

    override fun onCategoryItemClicked() {

    }

    fun getItems(): List<EventCategoryEntity> {
        val items = mutableListOf<EventCategoryEntity>()

        items.add(EventCategoryEntity(1, "Agriculture", ""))
        items.add(EventCategoryEntity(2, "Technology", ""))
        items.add(EventCategoryEntity(3, "Education", ""))
        items.add(EventCategoryEntity(4, "Legal", ""))
        items.add(EventCategoryEntity(5, "Arts & Culture", ""))
        items.add(EventCategoryEntity(6, "Health", ""))
        items.add(EventCategoryEntity(7, "International Relations & Development", ""))

        return items
    }


}