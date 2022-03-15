
package com.example.stylism_fashion_ecommerce.utils

object CheckForm {
    fun checkEmail(email: String): Boolean {
        /**
         * The entered email is valid if...
         * ... Not Empty
         * ... match email address pattern from android.util.Patterns.EMAIL_ADDRESS
         * */
        return if (email.isEmpty()) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    fun
            checkPassword(password: String): Boolean {
        return !(password.isEmpty() || password.length <= 4)
    }
}
