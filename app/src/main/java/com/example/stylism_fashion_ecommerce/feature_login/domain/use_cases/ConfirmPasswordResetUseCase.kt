package com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases

import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.ConfirmPasswordResetResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import javax.inject.Inject

class ConfirmPasswordResetUseCase @Inject constructor(
    private val repo: AuthRepo
) {
    operator fun invoke(
        code: String,
        newPassword: String,
        confirmPasswordResetResultListener: ConfirmPasswordResetResultListener
    ) = repo.confirmPasswordReset(
        code = code,
        newPassword = newPassword,
        confirmPasswordResetResultListener = confirmPasswordResetResultListener
    )
}