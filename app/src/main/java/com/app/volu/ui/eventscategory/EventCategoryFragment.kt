package com.app.volu.ui.eventscategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.volu.R
import com.app.volu.data.Resource
import com.app.volu.databinding.FragmentEventCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EventCategoryFragment : Fragment() {

    private lateinit var binding: FragmentEventCategoryBinding
    private lateinit var eventCategoryAdapter: EventCategoryAdapter
    private lateinit var recyclerView: RecyclerView
    private val eventCategoryViewModel: EventCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_event_category, container, false)

        setUpRecyclerView()

        binding.done.setOnClickListener {
            val categoryItems = eventCategoryAdapter.getSelectedCategorySet()

            Timber.d("selected categories : $categoryItems")

            //todo : make api request to edit user event category preference
            findNavController().navigate(R.id.navigate_to_main)
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventCategoryViewModel.getEventCategoriesFromApi()

        eventCategoryViewModel.getEventCategoriesResult().observe(viewLifecycleOwner, { result ->
            when (result.status) {

                Resource.Status.LOADING -> {
                    //todo: show progress bar
                }

                Resource.Status.ERROR -> {
                    //todo: show error view with message and retry button
                }

                Resource.Status.SUCCESS -> {
                    val categories = result.data ?: emptyList()
                    eventCategoryAdapter.setItems(categories)
                }
            }
        })

    }

    private fun setUpRecyclerView() {
        eventCategoryAdapter = EventCategoryAdapter()

        recyclerView = binding.categoryRecyclerView

        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = eventCategoryAdapter
        }
    }

}