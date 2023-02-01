package com.cockerspaniel.luncnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.cockerspaniel.luncnow.databinding.ActivityMainBinding
import com.cockerspaniel.luncnow.screen.base.BaseFragment
import com.cockerspaniel.luncnow.util.viewBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigationBar()
    }

    override fun onBackPressed() {
        // First handler is currently active fragment
        if ((currentFragment() as? BaseFragment)?.onBackPressed() != true) {
            // Second handler is back navigation
            if (!navController.popBackStack()) {
                // Fallback is Activity handling
                super.onBackPressed()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupBottomNavigationBar() {
        binding.navView.inflateMenu(R.menu.bottom_nav_menu)
        binding.navView.setupWithNavController(navController)
        binding.navView.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, navController)
            return@setOnItemSelectedListener true
        }
        binding.navView.setOnItemReselectedListener {
            val menuItemNavGraph = navController.graph.findNode(it.itemId) as NavGraph
            navController.popBackStack(menuItemNavGraph.startDestinationId, false)
        }
    }

    private fun currentFragment(): Fragment? {
        return supportFragmentManager
            .primaryNavigationFragment
            ?.childFragmentManager
            ?.fragments
            ?.lastOrNull()
    }
}
