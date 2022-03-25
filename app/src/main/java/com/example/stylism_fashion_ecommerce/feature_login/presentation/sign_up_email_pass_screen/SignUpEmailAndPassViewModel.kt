package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.Screens
import com.example.stylism_fashion_ecommerce.data.result_listeners.AddUserResultListener
import com.example.stylism_fashion_ecommerce.data.result_listeners.CreateWalletResultListener
import com.example.stylism_fashion_ecommerce.domain.models.User
import com.example.stylism_fashion_ecommerce.domain.models.Wallet
import com.example.stylism_fashion_ecommerce.domain.use_cases.AddUserUseCase
import com.example.stylism_fashion_ecommerce.domain.use_cases.CreateWalletUseCase
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignUpWithEmailAndPassResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases.SignUpWithEmailAndPasswordUseCase
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.example.stylism_fashion_ecommerce.utils.CheckForm
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignUpEmailAndPassViewModel @Inject constructor(
    private val signUpWithEmailAndPassUseCase: SignUpWithEmailAndPasswordUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val createWalletUseCase: CreateWalletUseCase
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

    /*********************************************************************************************************************/

    fun onEmailTextFieldValueChanges(newValue: String) {
        _emailTextField.value = newValue.trim()
    }

    fun onPassTextFieldValueChanges(newValue: String) {
        _passTextField.value = newValue.trim()
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


    fun signUpWithEmailAndPass(navController: NavController) {
        _isLoadingState.value = true
        if (CheckForm.checkEmail(email = emailTextField.value) && CheckForm.checkPassword(password = passTextField.value)) {
            signUpWithEmailAndPassUseCase(
                email = emailTextField.value,
                password = passTextField.value,
                signUpWithEmailAndPassResultListener = object :
                    SignUpWithEmailAndPassResultListener {
                    override fun onSuccess(firebaseUser: FirebaseUser) {
                        // add user to database
                        addUserToDatabase(
                            User(userId = UUID.randomUUID().toString()),
                            navController = navController
                        )
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

    fun addUserToDatabase(user: User, navController: NavController) {
        addUserUseCase(user = user, addUserResultListener = object : AddUserResultListener {
            override fun onSuccess() {
                Log.i(TAG, "onSuccess: User signed up successfully")
                createUserWallet(
                    wallet = Wallet(walletId = UUID.randomUUID().toString()),
                    navController = navController
                )
            }

            override fun onFailure(error: Throwable) {
                _isLoadingState.value = false
                Log.i(TAG, "onFailure: error -> ${error.message}")
                _doShowSignupFailedDialog.value = true
            }
        })
    }

    fun createUserWallet(wallet: Wallet, navController: NavController) {
        createWalletUseCase(
            wallet = wallet,
            createWalletResultListener = object : CreateWalletResultListener {
                override fun onSuccess() {
                    _isLoadingState.value = false
                    Log.i(TAG, "onSuccess: user wallet created successfully with 0$ as a balance")
                    Log.i(TAG, "onSuccess: Navigating to home...")
                    // TODO: Navigate to home
                }

                override fun onFailure(error: Throwable) {
                    _isLoadingState.value = false
                    Log.i(TAG, "onFailure: error -> ${error.message}")
                    _doShowSignupFailedDialog.value = true
                }
            })
    }
}