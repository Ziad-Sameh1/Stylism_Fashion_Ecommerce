package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignUpWithEmailAndPassResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases.SignUpWithEmailAndPasswordUseCase
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.example.stylism_fashion_ecommerce.utils.CheckForm
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpEmailAndPassViewModel @Inject constructor(
    private val signUpWithEmailAndPassUseCase: SignUpWithEmailAndPasswordUseCase
) : ViewModel() {

    private val _emailTextField = mutableStateOf<String>("")
    val emailTextField: State<String> = _emailTextField

    private val _passTextField = mutableStateOf<String>("")
    val passTextField: State<String> = _passTextField

    private val _isPassVisibleState = mutableStateOf<Boolean>(false)
    val isPassVisibleState: State<Boolean> = _isPassVisibleState

    private val _isLoadingState = mutableStateOf<Boolean>(false)
    val isLoadingState: State<Boolean> = _isLoadingState

    private val _doShowSignupFailedDialog = mutableStateOf<Boolean>(false)
    val doShowSignUpFailedDialog: State<Boolean> = _doShowSignupFailedDialog

    private val _doShowFormDialog = mutableStateOf<Boolean>(false)
    val doShowFormDialog: State<Boolean> = _doShowFormDialog

    private val _noInternetState = mutableStateOf<Boolean>(false)

    /*********************************************************************************************************************/

    fun onEmailTextFieldValueChanges(newValue: String) {
        _emailTextField.value = newValue
    }

    fun onPassTextFieldValueChanges(newValue: String) {
        _passTextField.value = newValue
    }

    fun onPassVisibleStateChanges(newValue: Boolean) {
        _isPassVisibleState.value = newValue
    }

    fun onDoShowSignupFailedDialog(newValue: Boolean) {
        _doShowSignupFailedDialog.value = newValue
    }

    fun onDoShowFormDialog(newValue: Boolean) {
        _doShowFormDialog.value = newValue
    }

    fun onIsLoadingState(newValue: Boolean) {
        _isLoadingState.value = newValue
    }

    /*********************************************************************************************************************/


    fun signUpWithEmailAndPass() {
        _isLoadingState.value = true
        if (CheckForm.checkEmail(email = emailTextField.value) && CheckForm.checkPassword(password = passTextField.value)) {
            signUpWithEmailAndPassUseCase(
                email = emailTextField.value,
                password = passTextField.value,
                signUpWithEmailAndPassResultListener = object :
                    SignUpWithEmailAndPassResultListener {
                    override fun onSuccess(firebaseUser: FirebaseUser) {
                        _isLoadingState.value = false
                        // TODO: Navigate to profile screen
                    }

                    override fun onFailure(error: Throwable) {
                        _isLoadingState.value = false
                        Log.i(TAG, "onFailure: error -> ${error.message}")
                        _doShowSignupFailedDialog.value = true
                    }

                })
        } else {
            _doShowFormDialog.value = true
        }
    }
}