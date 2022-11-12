package com.example.laundrymanager.Models

data class User(
    val userid: String,
    var email: String,
    var name: String,
    var password: String,
    var type: Boolean,
    var gender: Char,
    var age: Int
)