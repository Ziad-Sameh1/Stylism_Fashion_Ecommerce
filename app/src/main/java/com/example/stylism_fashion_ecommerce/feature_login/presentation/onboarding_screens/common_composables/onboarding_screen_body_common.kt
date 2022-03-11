package com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.common_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.stylism_fashion_ecommerce.ui.theme.poppinsFont

@Composable
fun OnboardingScreenBodyCommon(bodyContent: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = bodyContent),
            style = MaterialTheme.typography.body2.copy(
                fontFamily = poppinsFont,
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
            textAlign = TextAlign.Center
        )
    }
}