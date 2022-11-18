package com.example.topdark.vaderpages

import models.Globals
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.topdark.R
import com.example.topdark.databinding.ActivityVaderBinding

class Activity_Vader : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityVaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVaderBinding.inflate(layoutInflater)
        binding.TVName.text = Globals.user?.nickname
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_activity_vader) as NavHostFragment

        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        lateinit var navController: NavController
        if(Globals.user?.nickname == "admin" ||Globals.user?.username == "admin"){
            navController = findNavController(R.id.nav_host_fragment_content_activity_vader)
        } else {
            navController = findNavController(R.id.nav_host_fragment_content_pilot_main)
        }


        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}