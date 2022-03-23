package com.example.stylism_fashion_ecommerce.feature_login.presentation.enter_otp_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithPhoneNumberResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases.SignInWithPhoneNumberUseCase
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.PARAM_Phone
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnterOTPViewModel @Inject constructor(
    private val signInWithPhoneNumberUseCase: SignInWithPhoneNumberUseCase
) : ViewModel() {

    private val _isLoadingState = mutableStateOf<Boolean>(false)
    val isLoadingState: State<Boolean> = _isLoadingState

    private val _isErrorState = mutableStateOf<Boolean>(false)
    val isErrorState: State<Boolean> = _isErrorState

    private val _num1 = mutableStateOf<String>("")
    val num1: State<String> = _num1

    private val _num2 = mutableStateOf<String>("")
    val num2: State<String> = _num2

    private val _num3 = mutableStateOf<String>("")
    val num3: State<String> = _num3

    private val _num4 = mutableStateOf<String>("")
    val num4: State<String> = _num4

    private val _num5 = mutableStateOf<String>("")
    val num5: State<String> = _num5

    private val _num6 = mutableStateOf<String>("")
    val num6: State<String> = _num6

    private var _finalOTP = ""

    /*********************************************************************************************/

    fun onNum1Changes(newValue: String) {
        _num1.value = newValue
        _finalOTP = num1.value + num2.value + num3.value + num4.value + num5.value + num6.value
    }

    fun onNum2Changes(newValue: String) {
        _num2.value = newValue
        _finalOTP = num1.value + num2.value + num3.value + num4.value + num5.value + num6.value
    }

    fun onNum3Changes(newValue: String) {
        _num3.value = newValue
        _finalOTP = num1.value + num2.value + num3.value + num4.value + num5.value + num6.value
    }

    fun onNum4Changes(newValue: String) {
        _num4.value = newValue
        _finalOTP = num1.value + num2.value + num3.value + num4.value + num5.value + num6.value
    }

    fun onNum5Changes(newValue: String) {
        _num5.value = newValue
        _finalOTP = num1.value + num2.value + num3.value + num4.value + num5.value + num6.value
    }

    fun onNum6Changes(newValue: String) {
        _num6.value = newValue
        _finalOTP = num1.value + num2.value + num3.value + num4.value + num5.value + num6.value
    }

    fun onIsErrorStateChanges(newValue: Boolean) {
        _isErrorState.value = newValue
    }

    /***********************************************************************************************/
    fun verifyOTP() {
        _isLoadingState.value = true
        if (_finalOTP.length == 6 && _finalOTP.count { it.digitToInt() in 0..9 } == 6) {
            signInWithPhoneNumberUseCase(
                code = _finalOTP,
                signInWithPhoneNumberResultListener = object : SignInWithPhoneNumberResultListener {
                    override fun onSuccess() {
                        _isLoadingState.value = false
                        // TODO: Navigate to home page
                        Log.i(TAG, "onSuccess: Navigating to home...")
                    }

                    override fun onFailure(error: Throwable) {
                        _isLoadingState.value = false
                        _isErrorState.value = true
                        Log.i(TAG, "onFailure: error -> ${error.message}")
                    }

                })

        } else {
            _isLoadingState.value = false
            _isErrorState.value = true
        }
    }
}