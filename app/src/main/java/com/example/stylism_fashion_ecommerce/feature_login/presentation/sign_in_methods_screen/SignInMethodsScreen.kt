package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen.composables.SignInMethodsScreenChooseMethodColumn
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen.composables.SignInMethodsScreenImage

@Composable
fun SignInMethodsScreen(
    navController: NavController,
    viewModel: SignInMethodsViewModel
) {
    viewModel.changeIsFirstTime()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        SignInMethodsScreenImage(modifier = Modifier.weight(2f))
        SignInMethodsScreenChooseMethodColumn(
            navController = navController,
            modifier = Modifier.weight(2f)
        )
    }
}