package com.example.stylism_fashion_ecommerce.domain.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Wallet(
    val walletId: String,
    val balance: Map<String, Float> = mapOf("EGP" to 0.0f),
    val latestTransactions: List<Transaction> = emptyList(),
    @ServerTimestamp
    val dateCreated : Date? = null
)
