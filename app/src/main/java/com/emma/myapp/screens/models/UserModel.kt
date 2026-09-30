package com.emma.myapp.screens.models

data class User(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val userId: String = "",
    val userRole: String = "User"
)
data class UserLogin(
    val email: String = "",
    val password: String = ""
)