package com.example.volu.ui.onboarding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.volu.R
import com.example.volu.data.Constants
import com.example.volu.data.local.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var prefManager: PrefManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = view.findNavController()

        Timber.d("first time launch ${prefManager.getBooleanItem("has_launched")}")

        //todo: refactor to use theme approach for the splash screen
        Handler(Looper.getMainLooper()).postDelayed(
            {
                navigateToRightDestination(navController)
            },
            1000
        )
    }

    private fun navigateToRightDestination(navController: NavController) {
        if (prefManager.getBooleanItem(Constants.IS_LOGGED_IN)) {
            navController.navigate(R.id.navigate_to_main)
            return
        }

        if (prefManager.getBooleanItem(Constants.HAS_LAUNCHED)) {
            navController.navigate(R.id.navigate_to_login)
            return
        }

        navController.navigate(R.id.navigate_to_on_boarding)
    }
}