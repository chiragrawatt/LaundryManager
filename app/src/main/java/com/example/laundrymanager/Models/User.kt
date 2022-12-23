package com.example.laundrymanager.Models

data class User(
    val userid: String,
    val email: String,
    val name: String,
    val password: String,
    val type: Int,
    val gender: String,
    val age: Int
)