package com.example.stylism_fashion_ecommerce.domain.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class User(
    val userId: String,
    val userName: String? = null,
    val purchasedProducts : List<Product> = emptyList(),
    @ServerTimestamp
    val dateCreated : Date? = null
)