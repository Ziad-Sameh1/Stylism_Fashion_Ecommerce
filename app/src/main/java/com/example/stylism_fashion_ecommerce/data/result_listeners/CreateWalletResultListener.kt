package com.example.stylism_fashion_ecommerce.data.result_listeners

interface CreateWalletResultListener {
    fun onSuccess()
    fun onFailure(error: Throwable)
}