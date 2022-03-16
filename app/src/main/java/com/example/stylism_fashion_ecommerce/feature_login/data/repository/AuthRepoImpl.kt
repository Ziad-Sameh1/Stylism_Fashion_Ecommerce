package com.example.stylism_fashion_ecommerce.feature_login.data.repository

import android.util.Log
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.*
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepo {
    override fun signInWithEmailAndPass(
        email: String,
        password: String,
        signInWithEmailAndPassResultListener: SignInWithEmailAndPassResultListener
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.i(TAG, "signInWithEmailAndPass: User signed in successfully")
                signInWithEmailAndPassResultListener.onSuccess(user = firebaseAuth.currentUser!!)
            } else {
                Log.i(TAG, "signInWithEmailAndPass: User signing in failed")
                it.exception?.let { it1 -> signInWithEmailAndPassResultListener.onFailure(error = it1) }
            }
        }
    }

    override fun signUpWithEmailAndPass(
        email: String,
        password: String,
        signUpWithEmailAndPassResultListener: SignUpWithEmailAndPassResultListener
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseAuth.useAppLanguage()
                firebaseAuth.currentUser!!.sendEmailVerification().addOnCompleteListener { it2 ->
                    if (it2.isSuccessful) {
                        Log.i(TAG, "signUpWithEmailAndPass: User Signed Up Successfully")
                        signUpWithEmailAndPassResultListener.onSuccess(firebaseUser = firebaseAuth.currentUser!!)
                    }
                    else {
                        signUpWithEmailAndPassResultListener.onFailure(error = it.exception!!)
                    }
                }
            } else {
                Log.i(TAG, "signUpWithEmailAndPass: User Signing up failed!!")
                signUpWithEmailAndPassResultListener.onFailure(error = it.exception!!)
            }
        }
    }

    override fun sendResetPassResetEmail(
        email: String,
        sendResetPassResetEmailResultListener: SendResetPassResetEmailResultListener
    ) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.i(TAG, "sendResetPassResetEmail: Reset password email send successfully")
                sendResetPassResetEmailResultListener.onSuccess()
            } else {
                it.exception?.let { it1 -> sendResetPassResetEmailResultListener.onFailure(error = it1) }
            }
        }
    }

    override fun confirmPasswordReset(
        code: String,
        newPassword: String,
        confirmPasswordResetResultListener: ConfirmPasswordResetResultListener
    ) {
        firebaseAuth.confirmPasswordReset(code, newPassword).addOnCompleteListener {
            if (it.isSuccessful) {
                confirmPasswordResetResultListener.onSuccess()
            } else {
                it.exception?.let { it1 -> confirmPasswordResetResultListener.onFailure(error = it1) }
            }
        }
    }

    override fun getSignedInUser(): FirebaseUser? {
        Log.i(TAG, "getSignedInUser: Reloading the repo user")
        Log.i(TAG, "getSignedInUser: user repo : -> ${firebaseAuth.currentUser}")
        firebaseAuth.currentUser?.reload()
        return firebaseAuth.currentUser
    }
}