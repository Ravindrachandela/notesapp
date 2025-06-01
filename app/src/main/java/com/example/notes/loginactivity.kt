package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notes.databinding.ActivityLoginactivityBinding
import com.google.firebase.auth.FirebaseAuth

class loginactivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginactivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()
        binding.lgnbutton.setOnClickListener {
            login()
        }
    }
    private fun login(){
        val email=binding.eml.text.toString()
        val password =binding.pass.text.toString()
        if (email.isBlank()||password.isBlank()){
            Toast.makeText(this,"enter email and password", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"successfull" , Toast.LENGTH_SHORT).show()
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                    Toast.makeText(this,"error login ", Toast.LENGTH_SHORT).show()

            }
    }
}

