package com.flogiston.test.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.flogiston.test.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigationController = Navigation.findNavController(this, R.id.navFragment)
        tabs.setupWithNavController(navigationController)
        tabs.setOnNavigationItemReselectedListener {  }
    }
}
