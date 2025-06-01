package com.example.notes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityAllnotesactivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Allnotesactivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllnotesactivityBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllnotesactivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference
        recyclerView = binding.recyclerView
        recyclerView.layoutManager=LinearLayoutManager(this)
        val currentuser = auth.currentUser
        currentuser?.let {user->
            val notereference=databaseReference.child("users").child(user.uid).child("notes")
                notereference.addValueEventListener(object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                       val notelist= mutableListOf<NoteItem>()
                        for (notesnapshot in snapshot.children){
                            val Note =notesnapshot.getValue(NoteItem::class.java)
                            Note?.let{
                                notelist.add(it)
                            }
                        }
                        notelist.reverse()
                        val adapter = noteadapter(notelist)
                        recyclerView.adapter=adapter
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
        }
    }
}