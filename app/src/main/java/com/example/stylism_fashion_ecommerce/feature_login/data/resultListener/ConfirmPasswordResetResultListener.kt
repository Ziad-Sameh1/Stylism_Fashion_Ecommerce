package com.example.stylism_fashion_ecommerce.feature_login.data.resultListener

interface ConfirmPasswordResetResultListener {
    fun onSuccess()
    fun onFailure(error: Throwable)
}