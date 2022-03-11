package com.example.stylism_fashion_ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.FirstBoardingScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.SecondBoardingScreen
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen.ThirdBoardingScreen
import com.example.stylism_fashion_ecommerce.ui.theme.DarkSurface
import com.example.stylism_fashion_ecommerce.ui.theme.Stylism_Fashion_EcommerceTheme
import com.example.stylism_fashion_ecommerce.ui.theme.WhiteSurface
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
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
                    startDestination = Screens.FirstBoardingScreen.route
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
                }
            }
        }
    }
}