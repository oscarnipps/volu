package com.app.volu.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.volu.R
import kotlinx.android.synthetic.main.on_boarding_item.view.*

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private var items: List<OnBoardingItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.on_boarding_item, parent, false)

        return OnBoardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<OnBoardingItem>) {
        this.items = items
        notifyDataSetChanged()
    }


    class OnBoardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val onBoardingImage = itemView.imageOnboarding
        private val onBoardingTitle = itemView.textTitle
        private val onBoardingDescription = itemView.textDescription

        fun bindItem(currentItem: OnBoardingItem) {
            onBoardingImage.setImageDrawable(currentItem.drawable)

            onBoardingTitle.text = currentItem.title

            onBoardingDescription.text = currentItem.description
        }


    }
}