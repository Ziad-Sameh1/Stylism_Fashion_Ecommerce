package com.example.stylism_fashion_ecommerce.feature_login.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.feature_login.presentation.modifier_extensions.mirror

@Composable
fun CommonBackButton(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Go back",
                tint = MaterialTheme.colors.onSurface, modifier = Modifier.mirror()
            )
        }
    }
}