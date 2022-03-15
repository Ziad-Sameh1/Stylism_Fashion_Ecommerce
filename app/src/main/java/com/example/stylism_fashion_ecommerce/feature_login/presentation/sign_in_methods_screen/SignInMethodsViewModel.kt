package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInMethodsViewModel @Inject constructor(
    private val editor: SharedPreferences.Editor
) : ViewModel() {
    fun changeIsFirstTime() {
        editor.putBoolean(CONSTANTS.IS_FIRST_TIME, false)
        editor.commit()
        editor.apply()
    }
}