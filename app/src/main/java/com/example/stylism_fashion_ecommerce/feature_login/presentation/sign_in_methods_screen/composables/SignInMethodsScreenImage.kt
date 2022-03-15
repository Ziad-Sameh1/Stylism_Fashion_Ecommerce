package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.stylism_fashion_ecommerce.R

@Composable
fun SignInMethodsScreenImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_choose_login_method),
        contentDescription = "", modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
}