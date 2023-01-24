package com.example.laundrymanager.Models

data class UserResponse(
    val message: String,
    val user: User,
    val token: String
)