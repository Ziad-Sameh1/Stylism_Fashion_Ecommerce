package com.example.stylism_fashion_ecommerce.feature_login.data.resultListener

import com.google.firebase.auth.FirebaseUser

interface SignUpWithEmailAndPassResultListener {
    fun onSuccess(firebaseUser: FirebaseUser)
    fun onFailure(error: Throwable)
}