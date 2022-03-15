package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.composables.SignInWithEmailAndPassBody
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.composables.SignInWithEmailAndPassForm
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.composables.SignInWithEmailAndPassHeader
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.composables.SignInWithEmailAndPassImage
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.composables.alert_dialogs.FormDialog
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.composables.alert_dialogs.LoginFailedDialog
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun SignInWithEmailAndPasswordScreen(
    viewModel: SignInWithEmailAndPassViewModel,
    context: Context,
    navController: NavController
) {
    Box() {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface)
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**
             * First Half of the screen
             * */
            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * Image
                 * */
                SignInWithEmailAndPassImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(7f)
                )

                /**
                 * Header
                 * */
                SignInWithEmailAndPassHeader(modifier = Modifier.weight(1.3f))

                /**
                 * Body
                 * */
                SignInWithEmailAndPassBody(modifier = Modifier.weight(1f))
            }
            /**
             * Second Half of the screen
             * */
            Column(
                modifier = Modifier.weight(1.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignInWithEmailAndPassForm(
                    viewModel = viewModel,
                    context = context,
                    navController = navController
                )
            }
        }
        if (viewModel.isLoadingState.value) {
            Log.i(TAG, "SignInWithEmailAndPasswordScreen: Loading...")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (viewModel.doShowLoginFailedDialog.value) {
            viewModel.onIsLoadingState(false)
            Log.i(TAG, "SignInWithEmailAndPasswordScreen: login failed dialog")
            LoginFailedDialog(viewModel = viewModel)
        }
        if (viewModel.doShowFormDialog.value) {
            viewModel.onIsLoadingState(false)
            Log.i(TAG, "SignInWithEmailAndPasswordScreen: invalid info dialog")
            FormDialog(viewModel = viewModel)
        }
    }
}