package com.example.stylism_fashion_ecommerce.feature_login.presentation.sign_in_methods_screen.composables

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylism_fashion_ecommerce.R
import com.example.stylism_fashion_ecommerce.Screens
import com.example.stylism_fashion_ecommerce.ui.theme.poppinsFont
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG

@Composable
fun SignInMethodsScreenChooseMethodColumn(
    navController: NavController,
    modifier: Modifier = Modifier, context: Context
) {
    /**
     * Header
     * */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.h4.copy(
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /**
         * Sign in with Email And Pass Button
         * */
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Card(
                elevation = 8.dp,
                backgroundColor = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(15.dp),
                border = if (isSystemInDarkTheme()) BorderStroke(
                    width = 0.dp,
                    color = MaterialTheme.colors.surface
                ) else BorderStroke(width = 3.dp, color = MaterialTheme.colors.primary),
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screens.SignInWithEmailAndPassScreen.route) {
                            popUpTo(Screens.SignInMethodsScreen.route)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Sign in with email and password",
                        tint = if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = stringResource(id = R.string.sign_in_with_email_pass),
                        color = if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary,
                        modifier = Modifier.padding(vertical = 15.dp)
                    )
                }
            }
        }
        /**
         * Sign in with Phone number Button
         * */
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Card(
                elevation = 8.dp,
                backgroundColor = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(15.dp),
                border = if (isSystemInDarkTheme()) BorderStroke(
                    width = 0.dp,
                    color = MaterialTheme.colors.surface
                ) else BorderStroke(width = 3.dp, color = MaterialTheme.colors.primary),
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screens.SendOTPScreen.route) {
                            popUpTo(Screens.SignInMethodsScreen.route)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Sign in with phone number",
                        tint = if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = stringResource(id = R.string.sign_in_with_phone_number),
                        color = if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary,
                        modifier = Modifier.padding(vertical = 15.dp)
                    )
                }
            }
        }
        /**
         * Sign in with Google
         * */
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Card(
                elevation = 8.dp,
                backgroundColor = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(15.dp),
                border = if (isSystemInDarkTheme()) BorderStroke(
                    width = 0.dp,
                    color = MaterialTheme.colors.surface
                ) else BorderStroke(width = 3.dp, color = MaterialTheme.colors.primary),
            ) {
                Button(
                    onClick = {
                        Log.i(TAG, "SignInMethodsScreenChooseMethodColumn: sign in with google clicked")
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Sign in with Google", modifier = Modifier.size(24.dp),
                        tint = if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = stringResource(id = R.string.sign_in_with_google),
                        color = if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary,
                        modifier = Modifier.padding(vertical = 15.dp)
                    )
                }
            }
        }
    }
}