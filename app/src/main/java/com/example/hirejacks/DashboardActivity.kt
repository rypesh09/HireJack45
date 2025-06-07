package com.example.hirejacks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hirejacks.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // You can set up logout, profile, or navigation here
        binding.welcomeText.text = "Welcome to HireJacK!"
    }
}
