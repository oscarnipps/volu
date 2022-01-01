package com.app.volu.ui.eventscategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.volu.R
import com.app.volu.data.database.entities.EventCategoryEntity
import com.app.volu.databinding.EventCategoryItemBinding
import timber.log.Timber

class EventCategoryAdapter() :
    RecyclerView.Adapter<EventCategoryAdapter.EventCategoryViewHolder>() {

    private var categories = emptyList<EventCategoryEntity>()
    private var categorySet = mutableSetOf<EventCategoryEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val adapterBinding: EventCategoryItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.event_category_item,
            parent,
            false
        )

        return EventCategoryViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: EventCategoryViewHolder, position: Int) {
        val currentItem = categories[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setItems(eventCategoryEntityItems: List<EventCategoryEntity>) {
        categories = eventCategoryEntityItems
        Timber.d("category items ${eventCategoryEntityItems.size}")
        notifyDataSetChanged()
    }

    fun getSelectedCategorySet(): Set<EventCategoryEntity> {
        return categorySet
    }

    inner class EventCategoryViewHolder(val binding: EventCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.categoryItem.setOnClickListener {
                onCategoryItemClicked(categories[bindingAdapterPosition])
            }
        }

        private fun onCategoryItemClicked(item: EventCategoryEntity) {

            if (!categorySet.contains(item)) {
                categorySet.add(item)
                binding.selected.visibility = View.VISIBLE
                return
            }

            categorySet.remove(item)
            binding.selected.visibility = View.GONE
        }

        fun bind(currentItem: EventCategoryEntity) {
            binding.categoryName.text = currentItem.categoryName
        }
    }

}