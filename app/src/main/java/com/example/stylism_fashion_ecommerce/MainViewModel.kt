package com.example.stylism_fashion_ecommerce

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val firebaseAuth: FirebaseUser?,
) : ViewModel() {

    private val _isLoadingState = mutableStateOf<Boolean>(true)
    val isLoadingState: State<Boolean> = _isLoadingState

    private val _startDestination = mutableStateOf<String>(Screens.FirstBoardingScreen.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            /**
             * Here we need check if...
             * ... user opened the app for the first time -> open the first welcome screen
             * ... user opened the app before but he is not signed in -> open select your sign in method screen
             * ... user opened the app before and he is signed in before -> open the home screen
             * */
            if (sharedPreferences.getBoolean(CONSTANTS.IS_FIRST_TIME, true)) {
                Log.i(TAG, "First Time Opening")
                _startDestination.value = Screens.FirstBoardingScreen.route
            } else {
                if (firebaseAuth == null) {
                    Log.i(TAG, "Not First Time -> User not authenticated")
                    _startDestination.value = Screens.SignInMethodsScreen.route
                } else {
                    Log.i(TAG, "Not First Time -> User authenticated")
                    // TODO: open home screen
                }
            }
            _isLoadingState.value = false
        }
    }
}