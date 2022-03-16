package com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases

import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SendResetPassResetEmailResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import javax.inject.Inject

class SendResetPassEmailUseCase @Inject constructor(
    private val repo: AuthRepo
) {
    operator fun invoke(
        email: String,
        sendResetPassResetEmailResultListener: SendResetPassResetEmailResultListener
    ) = repo.sendResetPassResetEmail(
        email = email,
        sendResetPassResetEmailResultListener = sendResetPassResetEmailResultListener
    )
}