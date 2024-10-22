package com.example.spotifycopy

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class RegistrationViewModel: ViewModel() {
    val userRef = FirebaseDatabase.getInstance().reference.child("user")
    fun addUser(email: String, password: String) {
        userRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var maxUserId = 0
                for(child in snapshot.children) {
                    val userId = child.child("id").getValue<Int?>()
                    if(userId != null && userId > maxUserId)
                        maxUserId = userId
                }

                var newUser = User(maxUserId+1, email, password)
                userRef.child((maxUserId+1).toString()).setValue(newUser)
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}