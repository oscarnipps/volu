package com.example.volu.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.volu.R
import com.example.volu.data.Constants
import com.example.volu.data.local.PrefManager
import com.example.volu.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var onBoardingIndicator: LinearLayout
    private lateinit var navController: NavController

    @Inject
    lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_boarding, container, false)

        navController = findNavController()

        viewPager = binding.onBoardingViewPager

        onBoardingIndicator = binding.onBoardingIndicator

        setUpOnBoardingItems()

        viewPager.adapter = onBoardingAdapter

        setUpOnBoardingIndicators()

        setCurrentOnBoardingItemIndicator(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnBoardingItemIndicator(position)
            }

        })

        binding.buttonOnBoardingAction.setOnClickListener {

            if (isOnBoardingItemAtLastPosition()) {
                viewPager.currentItem = viewPager.currentItem + 1

            } else {
                prefManager.saveItem(Constants.HAS_LAUNCHED, true)

                navController.navigate(R.id.navigate_to_login)
            }

        }

        return binding.root
    }

    private fun isOnBoardingItemAtLastPosition(): Boolean {
        return viewPager.currentItem + 1 < onBoardingAdapter.itemCount
    }

    private fun setUpOnBoardingItems() {
        val onBoardingItems = mutableListOf<OnBoardingItem>()

        val onBoardingTitles = resources.getStringArray(R.array.on_boarding_titles)

        val onBoardingDescriptions = resources.getStringArray(R.array.on_boarding_descriptions)

        val onBoardingImages = resources.obtainTypedArray(R.array.on_boarding_images)

        for (index in onBoardingTitles.indices) {

            onBoardingItems.add(
                OnBoardingItem(
                    onBoardingImages.getDrawable(index)!!,
                    onBoardingTitles[index],
                    onBoardingDescriptions[index]
                )
            )

        }

        onBoardingAdapter = OnBoardingAdapter()

        onBoardingAdapter.setItems(onBoardingItems)

        onBoardingImages.recycle()
    }


    private fun setUpOnBoardingIndicators() {
        val indicators: Array<ImageView> = Array(onBoardingAdapter.itemCount) {
            ImageView(requireContext())
        }

        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(8, 0, 8, 0)

        for (index in indicators.indices) {
            indicators[index].setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.onboarding_indicator_inactive
                )
            )

            indicators[index].layoutParams = layoutParams

            onBoardingIndicator.addView(indicators[index])

        }

    }

    private fun setCurrentOnBoardingItemIndicator(index: Int) {
        val childCount: Int = onBoardingIndicator.childCount

        for (i in 0 until childCount) {
            val imageView = onBoardingIndicator.getChildAt(i) as ImageView

            if (i == index) {

                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.onboarding_indicator_active
                    )
                )

            } else {

                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.onboarding_indicator_inactive
                    )
                )

            }
        }

        binding.buttonOnBoardingAction.setText(getResId(index))
    }

    private fun getResId(index: Int): Int {
        //if it's the last item then use "get started" as the text
        return if (index == onBoardingAdapter.itemCount - 1) R.string.get_started else R.string.next
    }

}