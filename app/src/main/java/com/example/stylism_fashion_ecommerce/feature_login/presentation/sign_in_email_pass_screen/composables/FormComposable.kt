package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.composables

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.Color
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
import com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_email_pass_screen.SignInWithEmailAndPassViewModel
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.example.stylism_fashion_ecommerce.utils.CheckNetwork

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun SignInWithEmailAndPassForm(
    viewModel: SignInWithEmailAndPassViewModel,
    context: Context,
    navController: NavController
) {
    Column() {
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.weight(1f)) {
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
                    .weight(2f)
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
                ), shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
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
                    .weight(2f)
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
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable {
                            navController.navigate(Screens.ResetPasswordScreen.route) {
                                popUpTo(Screens.SignInWithEmailAndPassScreen.route)
                            }
                        },
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if (CheckNetwork.isInternetAvailable(context = context)) {
                                Log.i(TAG, "SignInWithEmailAndPassForm: Internet available")
                                viewModel.signInWithEmailAndPassword()
                            } else {
                                Log.i(TAG, "SignInWithEmailAndPassForm: Internet not available")
                                Toast.makeText(
                                    context,
                                    R.string.no_internet,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(50.dp))
                    ) {
                        Text(
                            text = stringResource(id = R.string.login),
                            modifier = Modifier.padding(vertical = 5.dp),
                            style = MaterialTheme.typography.body1.copy(
                                letterSpacing = 1.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(id = R.string.do_not_have_account))
                            append(" ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colors.primary,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append(stringResource(id = R.string.sign_up))
                            }
                        },
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                        modifier = Modifier.clickable {
                            navController.navigate(Screens.SignUpWithEmailAndPassScreen.route) {
                                popUpTo(Screens.SignInWithEmailAndPassScreen.route)
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}