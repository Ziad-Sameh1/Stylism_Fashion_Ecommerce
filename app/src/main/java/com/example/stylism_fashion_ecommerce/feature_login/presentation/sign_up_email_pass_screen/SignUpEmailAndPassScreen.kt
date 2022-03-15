package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.R
import com.example.stylism_fashion_ecommerce.Screens
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen.alert_dialogs.InvalidInfoDialog
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_up_email_pass_screen.alert_dialogs.SignUpFailedDialog
import com.example.stylism_fashion_ecommerce.ui.theme.poppinsFont
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.example.stylism_fashion_ecommerce.utils.CheckNetwork

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun SignUpWithEmailAndPasswordScreen(
    viewModel: SignUpEmailAndPassViewModel,
    context: Context,
    navController: NavController, focusManager: FocusManager
) {
    Box() {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.surface)
                .verticalScroll(scrollState)
                .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } }
        ) {
            /**
             * Back Button
             * */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                }
            }
            /**
             * Image
             * */
            Image(
                painter = painterResource(id = R.drawable.ic_sign_up),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )

            /**
             * Header
             * */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    style = MaterialTheme.typography.h3.copy(
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.7.sp
                    ), textAlign = TextAlign.Center, color = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            /**
             * Email Text Field
             * */
            TextField(
                value = viewModel.emailTextField.value,
                onValueChange = { newValue -> viewModel.onEmailTextFieldValueChanges(newValue = newValue) },
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
                        imageVector = Icons.Default.Person,
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
                ), shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            /**
             * Password Text Field
             * */
            TextField(
                value = viewModel.passTextField.value,
                onValueChange = { newValue -> viewModel.onPassTextFieldValueChanges(newValue = newValue) },
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
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Enter Your Password",
                        tint = MaterialTheme.colors.primary
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password),
                        color = MaterialTheme.colors.primary, fontWeight = FontWeight.SemiBold
                    )
                },
                trailingIcon = {
                    val image =
                        if (viewModel.isPassVisibleState.value) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    IconButton(onClick = {
                        viewModel.onPassVisibleStateChanges(!viewModel.isPassVisibleState.value)
                    }) {
                        Icon(
                            imageVector = image,
                            contentDescription = if (viewModel.isPassVisibleState.value) "Hide Password" else "Show Password",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                visualTransformation = if (viewModel.isPassVisibleState.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ), shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            /**
             * Sign up Button
             * */
            Button(
                onClick = {
                    if (CheckNetwork.isInternetAvailable(context = context)) {
                        Log.i(TAG, "SignUpWithEmailAndPasswordScreen: Internet Available")
                        viewModel.signUpWithEmailAndPass()
                    } else {
                        Log.i(TAG, "SignUpWithEmailAndPasswordScreen: Internet not available")
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
                    text = stringResource(id = R.string.sign_up),
                    modifier = Modifier.padding(vertical = 5.dp),
                    style = MaterialTheme.typography.body1.copy(
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            /**
             * Already have account
             * */

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.already_have_account))
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.primary,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(stringResource(id = R.string.sign_in))
                        }
                    },
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.SignInWithEmailAndPassScreen.route) {
                            popUpTo(Screens.SignInMethodsScreen.route)
                        }
                    }
                )
            }
        }
        if (viewModel.isLoadingState.value) {
            Log.i(TAG, "SignUpWithEmailAndPasswordScreen: Loading...")
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
        if (viewModel.doShowSignUpFailedDialog.value) {
            viewModel.onIsLoadingState(false)
            Log.i(TAG, "SignUpWithEmailAndPasswordScreen: Invalid sign up")
            SignUpFailedDialog(viewModel = viewModel)
        }
        if (viewModel.doShowFormDialog.value) {
            viewModel.onIsLoadingState(false)
            Log.i(TAG, "SignUpWithEmailAndPasswordScreen: Invalid entered info")
            InvalidInfoDialog(viewModel = viewModel)
        }
    }
}