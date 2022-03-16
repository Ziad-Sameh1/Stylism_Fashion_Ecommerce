package com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.R
import com.example.stylism_fashion_ecommerce.Screens
import com.example.stylism_fashion_ecommerce.feature_login.presentation.common.CommonBackButton
import com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.alert_dialogs.EmailNotFound
import com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.alert_dialogs.ResetPasswordFailed
import com.example.stylism_fashion_ecommerce.feature_login.presentation.reset_password_screen.alert_dialogs.WrongEmailDialog
import com.example.stylism_fashion_ecommerce.ui.theme.poppinsFont
import com.example.stylism_fashion_ecommerce.utils.CheckNetwork

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ResetPasswordScreen(
    viewModel: ResetPasswordViewModel,
    navController: NavController,
    context: Context, focusManager: FocusManager
) {
    val scrollState = rememberScrollState()
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.surface)
                .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } }
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            CommonBackButton(navController = navController)
            Image(
                painter = painterResource(id = R.drawable.ic_reset_password),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.h4.copy(
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.7.sp
                    ), textAlign = TextAlign.Center, color = MaterialTheme.colors.primary
                )
            }
            TextField(
                value = viewModel.enteredEmailState.value,
                onValueChange = { newValue -> viewModel.onEnteredEmailStateChanges(newValue = newValue) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                        alpha = 0.4f
                    ),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Enter Your Email", tint = MaterialTheme.colors.primary
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.email),
                        color = MaterialTheme.colors.primary, fontWeight = FontWeight.SemiBold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(10.dp)
            )
            Button(
                onClick = {
                    if (CheckNetwork.isInternetAvailable(context = context)) {
                        viewModel.sendResetPassEmail()
                    } else {
                        Toast.makeText(
                            context,
                            R.string.no_internet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clip(RoundedCornerShape(50.dp))
            ) {
                Text(
                    text = stringResource(id = R.string.reset),
                    modifier = Modifier.padding(vertical = 5.dp),
                    style = MaterialTheme.typography.body1.copy(
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
        if (viewModel.isLoadingState.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (viewModel.isEmailSent.value) {
            navController.navigate(Screens.SignInWithEmailAndPassScreen.route) {
                popUpTo(Screens.SignInMethodsScreen.route)
            }
            Toast.makeText(context, context.getString(R.string.email_sent), Toast.LENGTH_LONG)
                .show()
            viewModel.onIsEmailSentState(false)
        }
        if (viewModel.showEmailNotFoundDialogState.value) {
            EmailNotFound(viewModel = viewModel, navController = navController)
        } else if (viewModel.showResetPasswordFailedDialogState.value) {
            ResetPasswordFailed(viewModel = viewModel)
        } else if (viewModel.showWrongEmailDialogState.value) {
            WrongEmailDialog(viewModel = viewModel)
        }
    }
}