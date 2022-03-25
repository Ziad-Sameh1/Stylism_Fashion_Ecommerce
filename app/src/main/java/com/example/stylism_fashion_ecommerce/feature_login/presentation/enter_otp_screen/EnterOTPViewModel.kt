package com.example.stylism_fashion_ecommerce.feature_login.presentation.enter_otp_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.data.result_listeners.AddUserResultListener
import com.example.stylism_fashion_ecommerce.data.result_listeners.CreateWalletResultListener
import com.example.stylism_fashion_ecommerce.domain.models.User
import com.example.stylism_fashion_ecommerce.domain.models.Wallet
import com.example.stylism_fashion_ecommerce.domain.use_cases.AddUserUseCase
import com.example.stylism_fashion_ecommerce.domain.use_cases.CreateWalletUseCase
import com.example.stylism_fashion_ecommerce.feature_login.data.resultListener.SignInWithPhoneNumberResultListener
import com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases.SignInWithPhoneNumberUseCase
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EnterOTPViewModel @Inject constructor(
    private val signInWithPhoneNumberUseCase: SignInWithPhoneNumberUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val createWalletUseCase: CreateWalletUseCase
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
    fun verifyOTP(navController: NavController) {
        _isLoadingState.value = true
        if (_finalOTP.length == 6 && _finalOTP.count { it.digitToInt() in 0..9 } == 6) {
            signInWithPhoneNumberUseCase(
                code = _finalOTP,
                signInWithPhoneNumberResultListener = object : SignInWithPhoneNumberResultListener {
                    override fun onSuccess() {
                        addUserToDatabase(
                            user = User(userId = UUID.randomUUID().toString()),
                            navController = navController
                        )
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
                _isErrorState.value = true
            }
        })
    }

    fun createUserWallet(wallet: Wallet, navController: NavController) {
        createWalletUseCase(
            wallet = wallet,
            createWalletResultListener = object : CreateWalletResultListener {
                override fun onSuccess() {
                    _isLoadingState.value = false
                    Log.i(
                        TAG,
                        "onSuccess: User wallet created successfully with 0 EGP as a current balance"
                    )
                    Log.i(TAG, "onSuccess: Navigating to home...")
                    // TODO: Navigate to home
                }

                override fun onFailure(error: Throwable) {
                    _isLoadingState.value = false
                    Log.i(TAG, "onFailure: error -> ${error.message}")
                    _isErrorState.value = true
                }
            })
    }
}