package com.emma.myapp.ViewModel
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.emma.myapp.screens.models.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel (var navController: NavHostController, var context: Context) {
    init {
        try {
            if (FirebaseApp.getApps(context).isEmpty()) {
                FirebaseApp.initializeApp(context)
            }
        } catch (_: Exception) {
            // Ignore if initialization fails in preview
        }
    }

    private val auth: FirebaseAuth by lazy {
        try {
            FirebaseAuth.getInstance()
        } catch (e: Exception) {
            FirebaseApp.initializeApp(context)
            FirebaseAuth.getInstance()
        }
    }


    // Create a new user with email and password
    fun createUser(fullName: String, email: String, password: String, confirmPassword: String) {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(context, "Email and password can't be blank", Toast.LENGTH_SHORT).show()
        }else if (password != confirmPassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
        }else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userdata = User(fullName, email, password, confirmPassword, auth.currentUser!!.uid, "User")
                        // Save user data to database
                        val database = FirebaseDatabase.getInstance().getReference("users").
                        child("Users/"+auth.currentUser!!.uid)
                        database.setValue(userdata).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                                // Navigate to Log in screen
                                navController.navigate("login_screen")
                            } else {
                                Toast.makeText(
                                    context,
                                    "${it.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate("register_screen")
                            }
                        }
                    }else{
                        Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        navController.navigate("register_screen")
                    }
                }
        }
        // Sign in with email and password
        // Sign out function
    }
    fun signInUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                val database = auth.currentUser?.uid
                FirebaseDatabase.getInstance().getReference("users")
                        .child("Users/$database").get()
                        .addOnSuccessListener { snapshot -> val role =snapshot.child("role").value.toString()
                            if (role == "Admin"){
                                navController.navigate("dashboard_screen")
                            }else{
                                navController.navigate("user_dashboard")
                            }
                            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        }
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard_screen")
            }else{
                Toast.makeText(context,it.exception?.message?:"error logging", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun signOutUser() {
        auth.signOut()
        Toast.makeText(context, "Logout successful", Toast.LENGTH_SHORT).show()
        navController.navigate("login_screen")

    }
    //get current username function
    fun getCurrentUserName(onResult:(String) ->Unit){
        val userId=auth.currentUser?.uid
        if (userId==null) {
            onResult("user")
            return
        }
        FirebaseDatabase.getInstance().getReference("Users")
            .child(userId)
            .get()
            .addOnSuccessListener{snapshot ->
                val fullname=snapshot.child("fullname").getValue(String::class.java)
                onResult(fullname ?:"user")

            }
    }

}