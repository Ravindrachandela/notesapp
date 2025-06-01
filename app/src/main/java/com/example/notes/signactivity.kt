package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notes.databinding.ActivitySignactivityBinding
import com.google.firebase.auth.FirebaseAuth

class signactivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivitySignactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignactivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            finish()
            val intent = Intent(this, loginactivity::class.java)
            startActivity(intent)
        }
        binding.txt.setOnClickListener {
            val intent = Intent(this, loginactivity::class.java)
            startActivity(intent)
        }
        binding.sgnbutton.setOnClickListener {
            signup()
        }
    }


    private fun signup() {
        val email = binding.edtemail.text.toString()
        val password = binding.edtpassword.text.toString()
        val confirmpassword = binding.edtconfirmpassword.text.toString()

        if (email.isBlank() || password.isBlank() || confirmpassword.isBlank()) {
            Toast.makeText(this, "enter email and password", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmpassword) {
            Toast.makeText(this, "password and confirmpassword is not same", Toast.LENGTH_SHORT)
                .show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "successfull", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else
                    Toast.makeText(this, "error sign ", Toast.LENGTH_SHORT).show()

            }
    }
}