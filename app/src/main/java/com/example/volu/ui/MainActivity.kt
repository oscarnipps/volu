package com.example.volu.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.volu.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        /*val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_container) as NavHostFragment

        val navController = navHostFragment.navController*/
    }
}