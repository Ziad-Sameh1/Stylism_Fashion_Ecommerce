package com.example.stylism_fashion_ecommerce.feature_login.domain.repository

import android.app.Activity
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential

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

    fun getSignedInUser(): FirebaseUser?

    fun sendOTP(
        activity: Activity,
        phoneNumber: String,
        sendOTPResultListener: SendOTPResultListener,
        signInWithPhoneNumberResultListener: SignInWithPhoneNumberResultListener
    )

    fun signInWithPhoneNumber(
        code: String,
        signInWithPhoneNumberResultListener: SignInWithPhoneNumberResultListener
    )

    fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        signInWithPhoneNumberResultListener: SignInWithPhoneNumberResultListener
    )
}