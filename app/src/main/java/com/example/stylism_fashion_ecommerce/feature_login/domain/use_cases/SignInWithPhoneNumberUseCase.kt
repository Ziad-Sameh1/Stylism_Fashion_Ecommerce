package com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases

import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithPhoneNumberResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import javax.inject.Inject

class SignInWithPhoneNumberUseCase @Inject constructor(
    private val repo: AuthRepo
) {
    operator fun invoke(
        code: String,
        signInWithPhoneNumberResultListener: SignInWithPhoneNumberResultListener
    ) = repo.signInWithPhoneNumber(
        code = code,
        signInWithPhoneNumberResultListener = signInWithPhoneNumberResultListener
    )
}