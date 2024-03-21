package com.cs4520.assignment5.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.cs4520.assignment5.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)
        if (savedInstanceState == null) {
            val appHost = NavHostFragment.create(R.navigation.nav_graph)

            supportFragmentManager.commit {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, appHost)
                    .setPrimaryNavigationFragment(appHost)
                    .commit()
            }}}
}