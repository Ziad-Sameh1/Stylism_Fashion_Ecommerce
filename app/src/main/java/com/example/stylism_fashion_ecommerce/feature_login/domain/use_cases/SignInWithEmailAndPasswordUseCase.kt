package com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases

import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithEmailAndPassResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val repo: AuthRepo
) {
    operator fun invoke(
        email: String,
        password: String,
        signInWithEmailAndPassResultListener: SignInWithEmailAndPassResultListener
    ) = repo.signInWithEmailAndPass(
        email = email,
        password = password,
        signInWithEmailAndPassResultListener = signInWithEmailAndPassResultListener
    )
}