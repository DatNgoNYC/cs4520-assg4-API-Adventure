package com.cs4520.assignment4.Data.Entities

import androidx.room.Entity

@Entity(tableName = "products")
data class Product (
    val name: String,
    val type: String ,//can be food or equipment
    val expiryDate: String,
    val price: String
)