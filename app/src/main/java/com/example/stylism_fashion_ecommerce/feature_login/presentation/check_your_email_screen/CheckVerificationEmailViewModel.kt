package com.example.stylism_fashion_ecommerce.feature_login.presentation.check_your_email_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases.GetSignedInUserUseCase
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckVerificationEmailViewModel @Inject constructor(
    private val getSignedInUserUseCase: GetSignedInUserUseCase
) : ViewModel() {

    init {
        isUserVerified()
    }
    fun isUserVerified(): Boolean {
        return getSignedInUserUseCase()?.isEmailVerified == true
    }
}