package com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases

import android.app.Activity
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SendOTPResultListener
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithPhoneNumberResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import javax.inject.Inject

class SendOTPUseCase @Inject constructor(
    private val repo: AuthRepo
) {
    operator fun invoke(
        activity: Activity,
        phoneNumber: String,
        sendOTPResultListener: SendOTPResultListener,
        signInWithPhoneNumberResultListener: SignInWithPhoneNumberResultListener
    ) = repo.sendOTP(
        activity = activity,
        phoneNumber = phoneNumber,
        sendOTPResultListener = sendOTPResultListener,
        signInWithPhoneNumberResultListener = signInWithPhoneNumberResultListener
    )
}