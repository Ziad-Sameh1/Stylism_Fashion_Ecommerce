package com.example.stylism_fashion_ecommerce.feature_login.presentation.send_otp_screen

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SendOTPResultListener
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithPhoneNumberResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases.SendOTPUseCase
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.example.stylism_fashion_ecommerce.utils.CheckForm
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SendOTPViewModel @Inject constructor(
    private val sendOTPUseCase: SendOTPUseCase
) : ViewModel() {

    private val _isLoadingState = mutableStateOf(false)
    val isLoadingState: State<Boolean> = _isLoadingState

    private val _countryCode = mutableStateOf("+20")
    val countryCode: State<String> = _countryCode

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    private val _showErrorDialog = mutableStateOf(false)
    val showErrorDialog: State<Boolean> = _showErrorDialog

    private val _doNavigate = mutableStateOf<Boolean>(false)
    val doNavigate: State<Boolean> = _doNavigate

    private val _fullPhoneNumber = mutableStateOf<String>("")
    val fullPhoneNumber: State<String> = _fullPhoneNumber

    /***********************************************************************************************/

    fun onDoNavigateStateChanges(newValue: Boolean) {
        _doNavigate.value = newValue
    }

    fun onCountryCodeChanges(newValue: String) {
        _countryCode.value = newValue
        _fullPhoneNumber.value = _countryCode.value + _phoneNumber.value
    }

    fun onPhoneNumberChanges(newValue: String) {
        _phoneNumber.value = newValue
        _fullPhoneNumber.value = _countryCode.value + _phoneNumber.value
    }

    fun onShowErrorDialogStateChanges(newValue: Boolean) {
        _showErrorDialog.value = newValue
    }

    /***********************************************************************************************/

    fun sendOTP(activity: Activity) {
        _isLoadingState.value = true
        if (CheckForm.checkPhoneNumber(
                _fullPhoneNumber.value.trim().filter { !it.isWhitespace() })
        ) {
            sendOTPUseCase(
                activity = activity,
                phoneNumber = _fullPhoneNumber.value,
                sendOTPResultListener = object : SendOTPResultListener {
                    override fun onSuccess() {
                        _isLoadingState.value = false
                        _doNavigate.value = true
                        Log.i(TAG, "onSuccess: otp sent successfully")
                    }
                },
                signInWithPhoneNumberResultListener = object : SignInWithPhoneNumberResultListener {
                    override fun onSuccess() {
                        _isLoadingState.value = false
                        Log.i(TAG, "onSuccess: otp sent successfully")
                    }

                    override fun onFailure(error: Throwable) {
                        Log.i(TAG, "onFailure: error -> ${error.message}")
                        _isLoadingState.value = false
                        _showErrorDialog.value = true
                    }

                })
        } else {
            _isLoadingState.value = false
            _showErrorDialog.value = true
        }
    }
}