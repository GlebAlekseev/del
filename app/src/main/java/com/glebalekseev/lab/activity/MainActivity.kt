package com.glebalekseev.lab.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.glebalekseev.lab.R


class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity >> "
    }

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupActionBarWithNavController()
        setupDestinationChangeListener()

        Log.i(TAG, "onCreate")
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun setupActionBarWithNavController() {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun setupDestinationChangeListener() {
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.mainFragment -> supportActionBar?.title = getString(R.string.note_list)
                R.id.noteDetailFragment -> supportActionBar?.title =
                    getString(R.string.note_description)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }
}