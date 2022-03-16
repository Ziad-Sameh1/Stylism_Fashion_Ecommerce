package com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SendResetPassResetEmailResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases.SendResetPassEmailUseCase
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.example.stylism_fashion_ecommerce.utils.CheckForm
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val sendResetPassEmailUseCase: SendResetPassEmailUseCase
) : ViewModel() {

    private val _enteredEmailState = mutableStateOf<String>("")
    val enteredEmailState: State<String> = _enteredEmailState

    private val _isLoadingState = mutableStateOf<Boolean>(false)
    val isLoadingState: State<Boolean> = _isLoadingState

    private val _showEmailNotFoundDialogState = mutableStateOf<Boolean>(false)
    val showEmailNotFoundDialogState: State<Boolean> = _showEmailNotFoundDialogState

    private val _showResetPasswordFailedDialogState = mutableStateOf<Boolean>(false)
    val showResetPasswordFailedDialogState: State<Boolean> = _showResetPasswordFailedDialogState

    private val _showWrongEmailDialogState = mutableStateOf<Boolean>(false)
    val showWrongEmailDialogState: State<Boolean> = _showWrongEmailDialogState

    private val _isEmailSent = mutableStateOf<Boolean>(false)
    val isEmailSent: State<Boolean> = _isEmailSent

    /***********************************************************************************************/

    fun onEnteredEmailStateChanges(newValue: String) {
        _enteredEmailState.value = newValue.trim()
    }

    fun onShowEmailNotFoundDialogState(newValue: Boolean) {
        _showEmailNotFoundDialogState.value = newValue
    }

    fun onShowResetPasswordFailedDialogState(newValue: Boolean) {
        _showResetPasswordFailedDialogState.value = newValue
    }

    fun onShowWrongEmailDialogState(newValue: Boolean) {
        _showWrongEmailDialogState.value = newValue
    }

    fun onIsEmailSentState(newValue: Boolean) {
        _isEmailSent.value = newValue
    }

    /***********************************************************************************************/


    fun sendResetPassEmail() {
        _isLoadingState.value = true
        if (CheckForm.checkEmail(email = enteredEmailState.value)) {
            sendResetPassEmailUseCase(
                email = enteredEmailState.value,
                sendResetPassResetEmailResultListener = object :
                    SendResetPassResetEmailResultListener {
                    override fun onSuccess() {
                        _isLoadingState.value = false
                        _isEmailSent.value = true
                    }

                    override fun onFailure(error: Throwable) {
                        _isLoadingState.value = false
                        Log.i(
                            TAG,
                            "onFailure: send reset pass email failed error -> ${error},  ${error.message}"
                        )
                        if (error.message == "There is no user record corresponding to this identifier. The user may have been deleted.") {
                            _showEmailNotFoundDialogState.value = true
                        } else {
                            _showResetPasswordFailedDialogState.value = true
                        }
                    }
                })
        } else {
            _isLoadingState.value = false
            _showWrongEmailDialogState.value = true
        }
    }
}