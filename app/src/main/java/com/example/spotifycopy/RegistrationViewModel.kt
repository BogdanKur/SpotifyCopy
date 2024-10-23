package com.example.spotifycopy

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class RegistrationViewModel: ViewModel() {
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    val userRef = FirebaseDatabase.getInstance().reference.child("user")
    val auth = FirebaseAuth.getInstance()
    fun addUser(context: Context, navController: NavController) {
        userRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var maxUserId = 0
                for(child in snapshot.children) {
                    val userId = child.child("id").getValue<Int?>()
                    if(userId != null && userId > maxUserId)
                        maxUserId = userId
                }
                if(password.value.toString().length <= 6) {
                    Toast.makeText(context, "Пароль должен быть больше 6 знаков", Toast.LENGTH_SHORT).show()
                } else {
                    if(email.value.toString().contains("@mail.ru") || email.value.toString().contains("@gmail.com")) {
                        val newUser = User(maxUserId+1, email.value.toString(), password.value.toString())
                        userRef.child((maxUserId+1).toString()).setValue(newUser)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show()
                                    Log.d("Firebase", "User added successfully")
                                } else {
                                    Toast.makeText(context, "Вы чут чут проебались", Toast.LENGTH_SHORT).show()
                                    Log.w("Firebase", "Error adding user", task.exception)
                                }
                            }

                        auth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
                        navController.navigate(R.id.action_regFragment_to_distributionFragment)
                    } else Toast.makeText(context, "Данная почта не подходит", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}