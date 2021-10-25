package com.example.paging.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.paging.R
import com.example.paging.databinding.MainActivityBinding
import com.example.paging.ext.setupWithNavController
import com.example.paging.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {
    private val mainViewModel: MainViewModel by viewModels()
    private var currentNavController: LiveData<NavController>? = null

    override val layoutId: Int
        get() = R.layout.main_activity

    override fun getVM(): MainViewModel = mainViewModel

    override fun bindVM(binding: MainActivityBinding, vm: MainViewModel) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navGraphIds = listOf(
            R.navigation.graph_characters,
            R.navigation.graph_episodes,
            R.navigation.graph_locations
        )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainerView,
            intent = intent
        )

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean =
        currentNavController?.value?.navigateUp() ?: false

    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() != true) {
            super.onBackPressed()
        }
    }

}