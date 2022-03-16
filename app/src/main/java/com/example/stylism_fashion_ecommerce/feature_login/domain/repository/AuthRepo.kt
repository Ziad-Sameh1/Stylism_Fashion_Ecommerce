package com.example.stylism_fashion_ecommerce.feature_login.domain.repository

import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.*
import com.google.firebase.auth.FirebaseUser

interface AuthRepo {
    fun signInWithEmailAndPass(
        email: String,
        password: String,
        signInWithEmailAndPassResultListener: SignInWithEmailAndPassResultListener
    )

    fun signUpWithEmailAndPass(
        email: String,
        password: String,
        signUpWithEmailAndPassResultListener: SignUpWithEmailAndPassResultListener
    )

    fun sendResetPassResetEmail(
        email: String,
        sendResetPassResetEmailResultListener: SendResetPassResetEmailResultListener
    )

    fun confirmPasswordReset(
        code: String,
        newPassword: String,
        confirmPasswordResetResultListener: ConfirmPasswordResetResultListener
    )

    fun getSignedInUser() : FirebaseUser?
}