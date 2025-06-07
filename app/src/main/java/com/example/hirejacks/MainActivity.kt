package com.example.hirejacks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hirejacks.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var auth: FirebaseAuth
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Show splash screen first
        Thread.sleep(2000)
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // Enable ViewBinding and set layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase Auth
        auth = FirebaseAuth.getInstance()
        enableEdgeToEdge()

        // Sign In → Sign Up
        binding.signIn.setOnClickListener {
            startActivity(Intent(this, signupactivity::class.java))
            finish()
        }

        // Sign Out
        binding.signOut.setOnClickListener {
            auth.signOut()
            binding.userDetails.text = updateData()
        }

        // Edge-to-edge UI padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Button navigation
        val signInButton = findViewById<Button>(R.id.signIn)
        val signUpButton = findViewById<Button>(R.id.signOut)

        signInButton.setOnClickListener {
            startActivity(Intent(this, signinactivity::class.java))
        }

        signUpButton.setOnClickListener {
            startActivity(Intent(this, signupactivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.userDetails.text = updateData()
    }

    private fun updateData(): String {
        return "Email : ${auth.currentUser?.email ?: "Not logged in"}"
    }
}
