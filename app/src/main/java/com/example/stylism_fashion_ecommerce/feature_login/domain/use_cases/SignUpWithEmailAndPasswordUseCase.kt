package com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases

import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignUpWithEmailAndPassResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import javax.inject.Inject

class SignUpWithEmailAndPasswordUseCase @Inject constructor(
    private val repo: AuthRepo
) {
    operator fun invoke(
        email: String,
        password: String,
        signUpWithEmailAndPassResultListener: SignUpWithEmailAndPassResultListener
    ) = repo.signUpWithEmailAndPass(
        email = email,
        password = password,
        signUpWithEmailAndPassResultListener = signUpWithEmailAndPassResultListener
    )
}