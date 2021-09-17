package com.example.volu.ui.eventscategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.volu.R
import com.example.volu.data.local.EventCategory
import com.example.volu.databinding.EventCategoryItemBinding
import timber.log.Timber

class EventCategoryAdapter(var categories : List<EventCategory>) : RecyclerView.Adapter<EventCategoryAdapter.EventCategoryViewHolder>() {

    private var selectedItems = mutableSetOf<EventCategory>()

    interface EventCategoryInterface {
        fun onCategoryItemClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventCategoryViewHolder {
        //makes it the view holder's responsibility to know which layout to inflate
        return EventCategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EventCategoryViewHolder, position: Int) {
         val currentItem = categories[position]
         holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setItems(eventCategoryItems: List<EventCategory>) {
        categories = eventCategoryItems
        Timber.d("category items ${eventCategoryItems.size}")
        notifyDataSetChanged()
    }

    class EventCategoryViewHolder private constructor(val binding: EventCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.categoryItem.setOnClickListener {
                onItemClicked(binding)
            }
        }

        private fun onItemClicked(adapterBinding: EventCategoryItemBinding) {

            if (binding.selected.isVisible) {
                Toast.makeText(
                    adapterBinding.root.context,
                    "item at position",
                    Toast.LENGTH_SHORT
                ).show()

                binding.selected.visibility = View.GONE
                return
            }

            binding.selected.visibility = View.VISIBLE
        }

        fun bind(currentItem: EventCategory) {
            binding.categoryName.text = currentItem.categoryName
        }

        companion object {
            fun from(parent: ViewGroup): EventCategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val adapterBinding: EventCategoryItemBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.event_category_item,
                    parent,
                    false
                )

                return EventCategoryViewHolder(adapterBinding)
            }
        }
    }

}