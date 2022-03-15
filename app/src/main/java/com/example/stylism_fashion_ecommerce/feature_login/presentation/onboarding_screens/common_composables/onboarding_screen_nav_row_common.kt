package com.example.stylism_fashion_ecommerce.feature_login.presentation.onboarding_screens.common_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.Screens
import com.example.stylism_fashion_ecommerce.feature_login.presentation.modifier_extensions.mirror

@Composable
fun OnboardingScreenNavRowCommon(
    position: Int,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(modifier = modifier.fillMaxWidth()) {
        if (position != 1) {
            Surface(
                modifier = Modifier
                    .align(Alignment.CenterStart), elevation = 5.dp, shape = CircleShape
            ) {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go back",
                        tint = MaterialTheme.colors.primary, modifier = Modifier.mirror()
                    )
                }
            }
        }
        when (position) {
            1 -> {
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(15.dp)
                            .background(MaterialTheme.colors.primary, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        Modifier
                            .size(10.dp)
                            .background(
                                MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        Modifier
                            .size(10.dp)
                            .background(
                                MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    )
                }
            }
            2 -> {
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(10.dp)
                            .background(
                                MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        Modifier
                            .size(15.dp)
                            .background(MaterialTheme.colors.primary, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        Modifier
                            .size(10.dp)
                            .background(
                                MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    )
                }
            }
            3 -> {
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(10.dp)
                            .background(
                                MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        Modifier
                            .size(10.dp)
                            .background(
                                MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        Modifier
                            .size(15.dp)
                            .background(MaterialTheme.colors.primary, shape = CircleShape)
                    )
                }
            }
        }
        Surface(
            modifier = Modifier
                .align(Alignment.CenterEnd), elevation = 5.dp, shape = CircleShape
        ) {
            IconButton(onClick = {
                when (position) {
                    1 -> {
                        navController.navigate(Screens.SecondBoardingScreen.route) {
                            popUpTo(Screens.FirstBoardingScreen.route)
                        }
                    }
                    2 -> {
                        navController.navigate(Screens.ThirdBoardingScreen.route) {
                            popUpTo(Screens.SecondBoardingScreen.route)
                        }
                    }
                    3 -> {
                        navController.navigate(Screens.SignInMethodsScreen.route) {
                            popUpTo(Screens.SignInMethodsScreen.route)
                        }
                    }
                }
            }, modifier = Modifier.background(MaterialTheme.colors.primary, shape = CircleShape)) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next Page",
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.mirror()
                )
            }
        }
    }
}