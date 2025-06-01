package com.example.notes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notes.databinding.ActivityAddnoteactivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class addnoteactivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityAddnoteactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityAddnoteactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().reference
        firebaseAuth =FirebaseAuth.getInstance()
        binding.adbtn.setOnClickListener {
             val title =binding.edttitle.text.toString()
            val discription = binding.edtdiscription.text.toString()
            if(title.isBlank()||discription.isBlank()){
                Toast.makeText(this, "fill both the fields", Toast.LENGTH_SHORT).show()}
            else{
                val currentuser =firebaseAuth.currentUser
                currentuser?.let { user->
                    //genrate unique key for note
                   val notekey=databaseReference.child("users").child(user.uid).child("notes").push().key.toString()
                   //noteitem instance
                   val noteitem =NoteItem(title,discription)
                   if(notekey!=null){
                       //add note item
                       databaseReference.child("users").child(user.uid).child("notes").child(notekey).setValue(noteitem)
                           .addOnCompleteListener{task->
                               if(task.isSuccessful){
                                   Toast.makeText(this, "add successfully", Toast.LENGTH_SHORT).show()
                               }
                               else{
                                   Toast.makeText(this, "not successful", Toast.LENGTH_SHORT).show()
                               }
                           }
                   } 
                }
            }

        }
    }
}