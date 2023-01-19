package com.cockerspaniel.luncnow.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.cockerspaniel.luncnow.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
        navHostFragment?.findNavController()?.setGraph(R.navigation.nav_graph)
    }
}
