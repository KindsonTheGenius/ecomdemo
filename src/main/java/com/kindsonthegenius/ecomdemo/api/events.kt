package com.kindsonthegenius.ecomdemo.api

import java.util.*

data class OrderCreatedEvent(
        val orderId: UUID,
        val price: Double,
        val number:Int,
        val productId:String
)

data class ProductAddedEvent(
        val id: String,
        val price: Double,
        val stock:Int,
        val description: String
)

data class StockUpdatedEvent(
        val id:String,
        val stock: Int
)