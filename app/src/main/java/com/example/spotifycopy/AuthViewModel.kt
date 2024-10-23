package com.example.spotifycopy

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class AuthViewModel: ViewModel() {
    var email = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")
    val userRef = FirebaseDatabase.getInstance().reference.child("user")
    val auth = FirebaseAuth.getInstance()
    fun authUser(context: Context, navController: NavController) {
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snap in snapshot.children) {
                    if(email.value.toString() == snap.child("email").getValue<String?>() &&
                        password.value.toString() == snap.child("password").getValue<String?>()) {
                        auth.signInWithEmailAndPassword(email.value.toString(), password.value.toString())
                            .addOnSuccessListener {
                                navController.navigate(R.id.action_authFragment_to_mainFragment)
                                Log.e("authentication", "Success authentication")
                            }
                            .addOnCanceledListener{
                                Log.e("authentication", "Failed authentication")
                            }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}