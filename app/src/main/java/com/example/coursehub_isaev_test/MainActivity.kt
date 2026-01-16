package com.example.coursehub_isaev_test

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.di.AppDependenciesProvider
import com.example.coursehub_isaev_test.databinding.ActivityMainBinding
import com.example.feature_main.di.DaggerMainComponent
import com.example.feature_main.di.MainComponent
import com.example.feature_main.presentation.MainComponentProvider


class MainActivity : AppCompatActivity(), MainComponentProvider {

    private lateinit var binding: ActivityMainBinding
    private val _mainComponent: MainComponent by lazy {
        val dependencies = (application as AppDependenciesProvider).getDependencies()
        DaggerMainComponent.builder()
            .appDependencies(dependencies)
            .build()
    }

    override fun getMainComponent(): MainComponent = _mainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.auth_fragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }
}