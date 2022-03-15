package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.stylism_fashion_ecommerce.R

@Composable
fun SignInWithEmailAndPassImage(modifier: Modifier = Modifier) {
    Image(painter = painterResource(id = R.drawable.ic_login), contentDescription = "", modifier = modifier)
}