package com.example.stylism_fashion_ecommerce.feature_login.presentation.enter_otp_screen

import android.content.Context
import android.os.Build
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.R
import com.example.stylism_fashion_ecommerce.feature_login.presentation.common.CommonBackButton
import com.example.stylism_fashion_ecommerce.feature_login.presentation.send_otp_screen.alert_dialogs.ErrorDialog
import com.example.stylism_fashion_ecommerce.ui.theme.poppinsFont
import com.example.stylism_fashion_ecommerce.utils.CheckNetwork

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun EnterOTPScreen(
    viewModel: EnterOTPViewModel,
    navController: NavController,
    focusManager: FocusManager,
    context: Context, paramPhoneNumber: String
) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)
        .pointerInput(Unit) {
            detectTapGestures {
                focusManager.clearFocus()
            }
        }) {
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
                    text = stringResource(id = R.string.enter_otp),
                    style = MaterialTheme.typography.h4.copy(
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.7.sp
                    ), color = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(context.getString(R.string.enter_otp_desc))
                        append(" ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                            append(paramPhoneNumber)
                        }
                    },
                    style = MaterialTheme.typography.body1.copy(
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    ), color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                ) {
                    TextField(
                        value = viewModel.num1.value,
                        onValueChange = { newValue ->
                            if (newValue.length == 1) {
                                viewModel.onNum1Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Next
                                )
                            } else if (newValue.isEmpty()) {
                                viewModel.onNum1Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Previous
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Next
                            )
                        }),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black,
                            cursorColor = MaterialTheme.colors.primary,
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                                alpha = 0.4f
                            ),
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.primary
                        ),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                ) {
                    TextField(
                        value = viewModel.num2.value,
                        onValueChange = { newValue ->
                            if (newValue.length == 1) {
                                viewModel.onNum2Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Next
                                )
                            } else if (newValue.isEmpty()) {
                                viewModel.onNum2Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Previous
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Next
                            )
                        }),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black,
                            cursorColor = MaterialTheme.colors.primary,
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                                alpha = 0.4f
                            ),
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.primary
                        ),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                ) {
                    TextField(
                        value = viewModel.num3.value,
                        onValueChange = { newValue ->
                            if (newValue.length == 1) {
                                viewModel.onNum3Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Next
                                )
                            } else if (newValue.isEmpty()) {
                                viewModel.onNum3Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Previous
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Next
                            )
                        }),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black,
                            cursorColor = MaterialTheme.colors.primary,
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                                alpha = 0.4f
                            ),
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.primary
                        ),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                ) {
                    TextField(
                        value = viewModel.num4.value,
                        onValueChange = { newValue ->
                            if (newValue.length == 1) {
                                viewModel.onNum4Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Next
                                )
                            } else if (newValue.isEmpty()) {
                                viewModel.onNum4Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Previous
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Next
                            )
                        }),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black,
                            cursorColor = MaterialTheme.colors.primary,
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                                alpha = 0.4f
                            ),
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.primary
                        ),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                ) {
                    TextField(
                        value = viewModel.num5.value,
                        onValueChange = { newValue ->
                            if (newValue.length == 1) {
                                viewModel.onNum5Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Next
                                )
                            } else if (newValue.isEmpty()) {
                                viewModel.onNum5Changes(newValue = newValue)
                                focusManager.moveFocus(
                                    FocusDirection.Previous
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Next
                            )
                        }),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black,
                            cursorColor = MaterialTheme.colors.primary,
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                                alpha = 0.4f
                            ),
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.primary
                        ),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                ) {
                    TextField(
                        value = viewModel.num6.value,
                        onValueChange = { newValue ->
                            if (newValue.length == 1) {
                                viewModel.onNum6Changes(newValue = newValue)
                                focusManager.clearFocus()
                            } else if (newValue.isEmpty()) {
                                viewModel.onNum6Changes(newValue = newValue)
                                focusManager.clearFocus()
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        }),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.7f) else Color.Black,
                            cursorColor = MaterialTheme.colors.primary,
                            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
                                alpha = 0.4f
                            ),
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.primary
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    if (CheckNetwork.isInternetAvailable(context = context)) {
                        viewModel.verifyOTP()
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
                    text = stringResource(id = R.string.verify_btn),
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
        if (viewModel.isErrorState.value) {
            ErrorDialog(viewModel = viewModel)
        }
    }
}