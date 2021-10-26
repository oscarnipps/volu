package com.example.volu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.volu.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.main_nav_container) as NavHostFragment

        val navController = nestedNavHostFragment.navController

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            if (isTopLevelDestination(destination.id)) {
                bottomNavigationView.visibility = View.VISIBLE
                return@addOnDestinationChangedListener
            }

            bottomNavigationView.visibility = View.GONE
        }
        
        return view
    }

    private fun isTopLevelDestination(destination : Int): Boolean {
        val topLevelDestinations = getTopLevelDestinations()

        if (topLevelDestinations.contains(destination)) {
            return true
        }

        return false
    }

    private fun getTopLevelDestinations () : Set<Int>{
        return setOf(
            R.id.eventListFragment,
            R.id.exploreEventFragment,
            R.id.rewardsFragment,
            R.id.userProfileFragment
        )
    }

}