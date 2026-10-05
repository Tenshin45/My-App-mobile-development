package com.emma.myapp.screens.models

data class CloudinaryResponse(
    val url: String,
    val secure_url: String,
    val public_id: String,
    val width: Int,
    val height: Int,
    val format: String,
    val resource_type: String,
    val created_at: String,
    val bytes: Int,

)