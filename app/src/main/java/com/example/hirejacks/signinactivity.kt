package com.example.hirejacks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class signinactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Register"



        // Redirect to Sign Up screen
        binding.textView.setOnClickListener {
            startActivity(Intent(this, signupactivity::class.java))
            finish() // Prevent back to SignIn
        }

        // Login button logic
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passET.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                            finish() // Prevent returning to login
                        } else {
                            Toast.makeText(
                                this,
                                "Login failed. Check your credentials.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish() // Skip login if already signed in
        }
    }
}
