package com.example.stylism_fashion_ecommerce.feature_login.data.repository

import android.app.Activity
import android.util.Log
import com.example.stylism_fashion_ecommerce.SignedInUser
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.*
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepo {
    private var storedVerificationId = ""
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
                    } else {
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

    override fun sendOTP(
        activity: Activity,
        phoneNumber: String,
        sendOTPResultListener: SendOTPResultListener,
        signInWithPhoneNumberResultListener: SignInWithPhoneNumberResultListener
    ) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.i(TAG, "onVerificationCompleted: Verification done")
                signInWithPhoneAuthCredential(
                    credential = credential,
                    signInWithPhoneNumberResultListener = signInWithPhoneNumberResultListener
                )
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.i(TAG, "onVerificationFailed: OTP Verification Failed")
                signInWithPhoneNumberResultListener.onFailure(error = e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                sendOTPResultListener.onSuccess()
            }
        }
        firebaseAuth.useAppLanguage()
        val options = PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS).setActivity(activity).setCallbacks(callbacks).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun signInWithPhoneNumber(
        code: String, signInWithPhoneNumberResultListener: SignInWithPhoneNumberResultListener
    ) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
        signInWithPhoneAuthCredential(
            credential = credential,
            signInWithPhoneNumberResultListener = signInWithPhoneNumberResultListener
        )
    }

    override fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        signInWithPhoneNumberResultListener: SignInWithPhoneNumberResultListener
    ) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                SignedInUser.user = firebaseAuth.currentUser
                signInWithPhoneNumberResultListener.onSuccess()
            } else {
                signInWithPhoneNumberResultListener.onFailure(it.exception!!)
            }
        }
    }
}