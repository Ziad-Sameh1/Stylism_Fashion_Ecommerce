package com.example.stylism_fashion_ecommerce

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.FirstBoardingScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.SecondBoardingScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.ThirdBoardingScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.SignInWithEmailAndPassViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.SignInWithEmailAndPasswordScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen.SignInMethodsScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen.SignInMethodsViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen.SignUpEmailAndPassViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen.SignUpWithEmailAndPasswordScreen
import com.example.stylism_fashion_ecommerce.ui.theme.DarkSurface
import com.example.stylism_fashion_ecommerce.ui.theme.Stylism_Fashion_EcommerceTheme
import com.example.stylism_fashion_ecommerce.ui.theme.WhiteSurface
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val signInMethodsViewModel: SignInMethodsViewModel by viewModels()
    private val signInWithEmailAndPassViewModel: SignInWithEmailAndPassViewModel by viewModels()
    private val signUpWithEmailAndPassViewModel: SignUpEmailAndPassViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.isLoadingState.value
            }
        }
        setContent {
            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val context = LocalContext.current
            val focusManager = LocalFocusManager.current
            if (isSystemInDarkTheme()) {
                systemUiController.setStatusBarColor(
                    color = DarkSurface
                )
            } else {
                systemUiController.setStatusBarColor(
                    color = WhiteSurface
                )
            }
            Stylism_Fashion_EcommerceTheme {
                NavHost(
                    navController = navController,
                    startDestination = mainViewModel.startDestination.value
                ) {
                    composable(route = Screens.FirstBoardingScreen.route) {
                        FirstBoardingScreen(navController = navController)
                    }
                    composable(route = Screens.SecondBoardingScreen.route) {
                        SecondBoardingScreen(navController = navController)
                    }
                    composable(route = Screens.ThirdBoardingScreen.route) {
                        ThirdBoardingScreen(navController = navController)
                    }
                    composable(route = Screens.SignInMethodsScreen.route) {
                        SignInMethodsScreen(
                            navController = navController,
                            viewModel = signInMethodsViewModel
                        )
                    }
                    composable(route = Screens.SignInWithEmailAndPassScreen.route) {
                        SignInWithEmailAndPasswordScreen(
                            viewModel = signInWithEmailAndPassViewModel,
                            context = context, navController = navController
                        )
                    }
                    composable(route = Screens.SignUpWithEmailAndPassScreen.route) {
                        SignUpWithEmailAndPasswordScreen(
                            viewModel = signUpWithEmailAndPassViewModel,
                            context = context,
                            navController = navController, focusManager = focusManager
                        )
                    }
                }
            }
        }
    }
}