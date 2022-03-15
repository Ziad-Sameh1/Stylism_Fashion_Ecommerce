package com.example.stylism_fashion_ecommerce.feature_login.domain.repository

import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithEmailAndPassResultListener
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignUpWithEmailAndPassResultListener

interface AuthRepo {
    fun signInWithEmailAndPass(email: String, password: String, signInWithEmailAndPassResultListener: SignInWithEmailAndPassResultListener)
    fun signUpWithEmailAndPass(email: String, password: String, signUpWithEmailAndPassResultListener: SignUpWithEmailAndPassResultListener)
}