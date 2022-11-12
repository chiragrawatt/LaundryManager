package com.example.laundrymanager.Models

data class Order(
    val orderId: String,
    val custId: String,
    val employeeId: String,
    val orgId: String,
    var status: Boolean,
    var bill: Double,
    var amountPaid: Double,
    var noOfClothes: Int,
    var placeDate: String,
    var deliverDate: String
)