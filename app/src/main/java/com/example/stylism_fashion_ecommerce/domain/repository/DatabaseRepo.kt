package com.example.stylism_fashion_ecommerce.domain.repository

import com.example.stylism_fashion_ecommerce.data.result_listeners.AddUserResultListener
import com.example.stylism_fashion_ecommerce.data.result_listeners.CreateWalletResultListener
import com.example.stylism_fashion_ecommerce.domain.models.User
import com.example.stylism_fashion_ecommerce.domain.models.Wallet

interface DatabaseRepo {
    fun addUser(user: User, addUserResultListener: AddUserResultListener)
    fun createUserWallet(wallet: Wallet, createWalletResultListener: CreateWalletResultListener)
}