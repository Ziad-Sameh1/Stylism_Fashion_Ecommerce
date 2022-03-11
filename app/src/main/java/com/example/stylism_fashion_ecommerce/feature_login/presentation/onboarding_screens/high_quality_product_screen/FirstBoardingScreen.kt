package com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.high_quality_product_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.R
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.common_composables.OnboardingScreenBodyCommon
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.common_composables.OnboardingScreenHeaderCommon
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.common_composables.OnboardingScreenImageCommon
import com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.common_composables.OnboardingScreenNavRowCommon

@Composable
fun FirstBoardingScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OnboardingScreenImageCommon(
            drawableRes = R.drawable.ic_high_quality,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OnboardingScreenHeaderCommon(
            headerContent = R.string.high_quality_product,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OnboardingScreenBodyCommon(
            bodyContent = R.string.high_quality_product_desc,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OnboardingScreenNavRowCommon(
            position = 1,
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(horizontal = 30.dp)
        )
    }
}