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
import com.example.stylism_fashion_ecommerce.feature_login.presentation.check_your_email_screen.CheckVerificationEmailViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.check_your_email_screen.CheckYourEmailScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.enter_otp_screen.EnterOTPScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.enter_otp_screen.EnterOTPViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.FirstBoardingScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.SecondBoardingScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.ThirdBoardingScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.ResetPasswordScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.ResetPasswordViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.send_otp_screen.SendOTPScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.send_otp_screen.SendOTPViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.SignInWithEmailAndPassViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.SignInWithEmailAndPasswordScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen.SignInMethodsScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen.SignInMethodsViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen.SignUpEmailAndPassViewModel
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen.SignUpWithEmailAndPasswordScreen
import com.example.stylism_fashion_ecommerce.ui.theme.DarkSurface
import com.example.stylism_fashion_ecommerce.ui.theme.Stylism_Fashion_EcommerceTheme
import com.example.stylism_fashion_ecommerce.ui.theme.WhiteSurface
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val signInMethodsViewModel: SignInMethodsViewModel by viewModels()
    private val signInWithEmailAndPassViewModel: SignInWithEmailAndPassViewModel by viewModels()
    private val signUpWithEmailAndPassViewModel: SignUpEmailAndPassViewModel by viewModels()
    private val resetPasswordViewModel: ResetPasswordViewModel by viewModels()
    private val checkVerificationEmailViewModel: CheckVerificationEmailViewModel by viewModels()
    private val sendOTPViewModel: SendOTPViewModel by viewModels()
    private val enterOTPViewModel: EnterOTPViewModel by viewModels()

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
                            viewModel = signInMethodsViewModel, context = context
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
                    composable(route = Screens.ResetPasswordScreen.route) {
                        ResetPasswordScreen(
                            viewModel = resetPasswordViewModel,
                            navController = navController,
                            focusManager = focusManager,
                            context = context
                        )
                    }
                    composable(route = Screens.CheckYourEmailScreen.route) {
                        CheckYourEmailScreen(
                            navController = navController,
                            viewModel = checkVerificationEmailViewModel
                        )
                    }
                    composable(route = Screens.SendOTPScreen.route) {
                        SendOTPScreen(
                            viewModel = sendOTPViewModel,
                            navController = navController,
                            context = context, focusManager = focusManager
                        )
                    }
                    composable(route = Screens.EnterOTPScreen.route + "/{phoneNumber}") {
                        EnterOTPScreen(
                            viewModel = enterOTPViewModel,
                            navController = navController,
                            focusManager = focusManager,
                            context = context,
                            paramPhoneNumber = it.arguments?.getString(CONSTANTS.PARAM_Phone) ?: ""
                        )
                    }
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        checkVerificationEmailViewModel.isUserVerified()
    }
}