package com.example.stylism_fashion_ecommerce.feature_login.presentation.send_otp_screen

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.R
import com.example.stylism_fashion_ecommerce.Screens
import com.example.stylism_fashion_ecommerce.feature_login.presentation.common.CommonBackButton
import com.example.stylism_fashion_ecommerce.feature_login.presentation.enter_otp_screen.alert_dialogs.ErrorDialog
import com.example.stylism_fashion_ecommerce.ui.theme.poppinsFont
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG
import com.example.stylism_fashion_ecommerce.utils.CheckNetwork

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun SendOTPScreen(
    viewModel: SendOTPViewModel,
    navController: NavController,
    context: Context,
    focusManager: FocusManager
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonBackButton(navController = navController)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.enter_your_phone),
                    style = MaterialTheme.typography.h4.copy(
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.7.sp
                    ), color = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(context.getString(R.string.enter_your_phone_desc))
                        append(" ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                            append("Stylism")
                        }
                    },
                    style = MaterialTheme.typography.body1.copy(
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    ), color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(
                        text = stringResource(id = R.string.country_code),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    TextField(
                        value = viewModel.countryCode.value,
                        onValueChange = { newValue -> viewModel.onCountryCodeChanges(newValue = newValue) },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black,
                            cursorColor = MaterialTheme.colors.primary,
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                                alpha = 0.4f
                            ),
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.primary
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(
                                    FocusDirection.Next
                                )
                            }
                        )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(5f)
                ) {
                    Text(
                        text = stringResource(id = R.string.phone_number),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    TextField(
                        value = viewModel.phoneNumber.value,
                        onValueChange = { newValue -> viewModel.onPhoneNumberChanges(newValue = newValue) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Enter Your Phone",
                                tint = MaterialTheme.colors.primary
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.phone_number),
                                color = MaterialTheme.colors.primary
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black,
                            cursorColor = MaterialTheme.colors.primary,
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                                alpha = 0.4f
                            ),
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.primary
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    if (CheckNetwork.isInternetAvailable(context = context)) {
                        viewModel.sendOTP(context.getActivity()!!)
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.no_internet),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clip(RoundedCornerShape(50.dp))
            ) {
                Text(
                    text = stringResource(id = R.string.continue_btn),
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
        if (viewModel.showErrorDialog.value) {
            ErrorDialog(viewModel = viewModel)
        }
        if (viewModel.doNavigate.value) {
            navController.navigate(Screens.EnterOTPScreen.route + "/${viewModel.fullPhoneNumber.value}") {
                popUpTo(Screens.SendOTPScreen.route)
                Log.i(TAG, "SendOTPScreen: Sending phone Param -> ${viewModel.fullPhoneNumber.value}")
            }
            viewModel.onDoNavigateStateChanges(false)
        }
    }
}

/**
 * Extension function to get activity from context
 * */
fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}