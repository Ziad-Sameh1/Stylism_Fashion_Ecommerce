package com.example.stylism_fashion_ecommerce.feature_login.data.repository

import android.util.Log
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithEmailAndPassResultListener
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignUpWithEmailAndPassResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.google.firebase.auth.FirebaseAuth
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
                Log.i(TAG, "signUpWithEmailAndPass: User Signed Up Successfully")
                signUpWithEmailAndPassResultListener.onSuccess(firebaseUser = firebaseAuth.currentUser!!)
            } else {
                Log.i(TAG, "signUpWithEmailAndPass: User Signing up failed!!")
                signUpWithEmailAndPassResultListener.onFailure(error = it.exception!!)
            }
        }
    }
}