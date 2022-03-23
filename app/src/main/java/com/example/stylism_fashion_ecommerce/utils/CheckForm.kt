package com.example.stylism_fashion_ecommerce.utils

import android.util.Log
import android.util.Patterns
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG

object CheckForm {
    fun checkEmail(email: String): Boolean {
        /**
         * The entered email is valid if...
         * ... Not Empty
         * ... match email address pattern from android.util.Patterns.EMAIL_ADDRESS
         * */
        return if (email.trim().isEmpty()) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
        }
    }

    fun checkPassword(password: String): Boolean {
        return !(password.trim().isEmpty() || password.trim().length <= 4)
    }

    fun checkPhoneNumber(phoneNumber: String): Boolean {
        Log.i(TAG, "checkPhoneNumber: ${phoneNumber.trim()}")
        return if (phoneNumber.trim().isEmpty() || phoneNumber.trim().length < 10) {
            false
        } else {
            Patterns.PHONE.matcher(phoneNumber).matches()
        }
    }
}
