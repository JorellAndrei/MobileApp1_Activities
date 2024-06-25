package com.example.finez_activities

// Product.kt
data class Product(
    val name: String,
    val variation: String?,
    val price: String,
    val color: String?,
    val category: String,
    var size: String?  // Add size field
)
