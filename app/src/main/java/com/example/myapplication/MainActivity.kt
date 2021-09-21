package com.example.myapplication

import android.app.Notification
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener{ _, destination, _->
            when (destination.id) {
                R.id.homeFragment ->    bottomNav.visibility = View.VISIBLE
                R.id.discoverFragment ->    bottomNav.visibility = View.VISIBLE
                R.id.favouriteFragment ->    bottomNav.visibility = View.VISIBLE
                R.id.accountFragment ->    bottomNav.visibility = View.VISIBLE
                //else -> bottomNav.visibility = View.GONE

            }

        }

        val appBarConfiguration = AppBarConfiguration(
                setOf(R.id.homeFragment, R.id.discoverFragment, R.id.favouriteFragment, R.id.accountFragment)
        )

        setSupportActionBar(requireViewById(R.id.toolbarMain))


        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}