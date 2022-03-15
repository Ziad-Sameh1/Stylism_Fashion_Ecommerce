package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithEmailAndPassResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases.SignInWithEmailAndPasswordUseCase
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.example.stylism_fashion_ecommerce.utils.CheckForm
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInWithEmailAndPassViewModel @Inject constructor(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase
) : ViewModel() {

    private val _emailTextField = mutableStateOf<String>("")
    val emailTextField: State<String> = _emailTextField

    private val _passTextField = mutableStateOf<String>("")
    val passTextField: State<String> = _passTextField

    private val _isPassVisibleState = mutableStateOf<Boolean>(false)
    val isPassVisibleState: State<Boolean> = _isPassVisibleState

    private val _isLoadingState = mutableStateOf<Boolean>(false)
    val isLoadingState: State<Boolean> = _isLoadingState

    private val _doShowLoginFailedDialog = mutableStateOf<Boolean>(false)
    val doShowLoginFailedDialog: State<Boolean> = _doShowLoginFailedDialog

    private val _doShowFormDialog = mutableStateOf<Boolean>(false)
    val doShowFormDialog: State<Boolean> = _doShowFormDialog

    private val _noInternetState = mutableStateOf<Boolean>(false)
    val noInternetState: State<Boolean> = _noInternetState

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

    fun onDoShowLoginFailedDialog(newValue: Boolean) {
        _doShowLoginFailedDialog.value = newValue
    }

    fun onDoShowFormDialog(newValue: Boolean) {
        _doShowFormDialog.value = newValue
    }

    fun onDoShowNoInternetStateChanges(newValue: Boolean) {
        _noInternetState.value = newValue
    }

    fun onIsLoadingState(newValue: Boolean) {
        _isLoadingState.value = newValue
    }

    /*********************************************************************************************************************/

    fun signInWithEmailAndPassword() {
        /**
         * Check if the form is filled correctly...
         * ... if correctly -> Proceed to sign In
         * ... else show error dialog to the user
         * */
        _isLoadingState.value = true
        if (CheckForm.checkEmail(_emailTextField.value) && CheckForm.checkPassword(_passTextField.value)) {
            // Proceed to sign in
            signInWithEmailAndPasswordUseCase(
                email = emailTextField.value,
                password = passTextField.value,
                signInWithEmailAndPassResultListener = object :
                    SignInWithEmailAndPassResultListener {
                    override fun onSuccess(user: FirebaseUser) {
                        _isLoadingState.value = false
                        // TODO: Navigate To Home Screen 
                    }

                    override fun onFailure(error: Throwable) {
                        _isLoadingState.value = false
                        Log.i(TAG, "onFailure: error during signing in -> ${error.message}")
                        _doShowLoginFailedDialog.value = true
                    }

                })
        } else {
            _doShowFormDialog.value = true
        }
    }
}