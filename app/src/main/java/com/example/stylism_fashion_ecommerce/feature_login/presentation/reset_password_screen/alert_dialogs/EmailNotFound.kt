package com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.alert_dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.R
import com.example.stylism_fashion_ecommerce.Screens
import com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.ResetPasswordViewModel

@Composable
fun EmailNotFound(viewModel: ResetPasswordViewModel, navController: NavController) {
    AlertDialog(
        onDismissRequest = {
            viewModel.onShowEmailNotFoundDialogState(false)
        },
        title = {
            Text(text = stringResource(id = R.string.email_not_found))
        },
        text = {
            Text(text = stringResource(id = R.string.user_not_registered))
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        viewModel.onShowEmailNotFoundDialogState(false)
                        viewModel.onEnteredEmailStateChanges("")
                        navController.navigate(Screens.SignUpWithEmailAndPassScreen.route) {
                            popUpTo(
                                Screens.SignInMethodsScreen.route
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.sign_up))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { viewModel.onShowEmailNotFoundDialogState(false) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        }
    )
}