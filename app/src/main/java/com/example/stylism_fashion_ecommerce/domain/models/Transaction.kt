package com.example.stylism_fashion_ecommerce.domain.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Transaction(
    val transactionId: String,
    val walletId: String,
    val totalCost: String,
    @ServerTimestamp
    val date: Date? = null
)
