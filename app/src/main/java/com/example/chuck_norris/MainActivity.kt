package com.example.chuck_norris

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.chuck_norris.databinding.ActivityMainBinding
import com.example.chuck_norris.network.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private val bottomNavigationViewMain: BottomNavigationView by lazy { findViewById(R.id.bottomNavigationViewMain) }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navHostFragmentMain) as NavHostFragment?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    /**
     * Setup View Components
     */
    private fun setupViews() {
        setupNavigation()
        setupBottomNavigationView()
    }

    /**
     * Setup the toolbar
     */
    private fun setupBottomNavigationView() {
        bottomNavigationViewMain.setupWithNavController(navController)
    }

    /**
     * Setup navigation Components
     */
    private fun setupNavigation() {
        navHostFragment?.let { navController = it.findNavController() }
        navController.setGraph(R.navigation.main_graph)
    }
}

