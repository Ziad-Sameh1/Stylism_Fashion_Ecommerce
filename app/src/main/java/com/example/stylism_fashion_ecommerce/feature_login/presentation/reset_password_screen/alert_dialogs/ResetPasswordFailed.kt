package com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.alert_dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.stylism_fashion_ecommerce.R
import com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.ResetPasswordViewModel

@Composable
fun ResetPasswordFailed(viewModel: ResetPasswordViewModel) {
    AlertDialog(
        onDismissRequest = {
            viewModel.onShowResetPasswordFailedDialogState(false)
        },
        title = {
            Text(text = stringResource(id = R.string.reset_failed))
        },
        text = {
            Text(text = stringResource(id = R.string.error_occurred))
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
                    onClick = { viewModel.onShowResetPasswordFailedDialogState(false) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.try_again))
                }
            }
        }
    )
}