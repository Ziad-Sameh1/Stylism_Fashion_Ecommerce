package com.example.stylism_fashion_ecommerce.feature_login.data.resultListener

interface SignInWithPhoneNumberResultListener {
    fun onSuccess()
    fun onFailure(error: Throwable)
}