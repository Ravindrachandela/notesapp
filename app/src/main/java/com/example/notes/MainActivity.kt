package com.example.notes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.addbutton.setOnClickListener {
            val intent =Intent(this,addnoteactivity::class.java)
            startActivity(intent)
        }
        binding.allnotes.setOnClickListener {
            val intent =Intent(this,Allnotesactivity::class.java)
            startActivity(intent)
        }

    }
}