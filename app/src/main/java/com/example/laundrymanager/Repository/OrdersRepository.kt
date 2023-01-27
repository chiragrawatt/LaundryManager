package com.example.laundrymanager.Repository

import com.example.laundrymanager.Services.OrdersAPI
import javax.inject.Inject

class OrdersRepository @Inject constructor(private val ordersAPI: OrdersAPI) {
}