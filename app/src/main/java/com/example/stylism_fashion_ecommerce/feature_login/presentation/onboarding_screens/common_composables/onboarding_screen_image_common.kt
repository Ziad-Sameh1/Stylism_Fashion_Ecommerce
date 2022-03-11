package com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.common_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreenImageCommon(
    modifier: Modifier = Modifier,
    drawableRes: Int,
    contentDescription: String
) {
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = contentDescription,
        modifier = modifier.padding(horizontal = 10.dp)
    )
}