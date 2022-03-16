package com.example.stylism_fashion_ecommerce.feature_login.presentation.check_your_email_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS.TAG

@Composable
fun CheckYourEmailScreen(navController: NavController, viewModel: CheckVerificationEmailViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_check_email),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.check_email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (viewModel.isUserVerified()) {
                    // TODO: navigate to home page
                    Log.i(TAG, "CheckYourEmailScreen: Navigating to home...")
                }
            }) {
                Text(
                    text = stringResource(id = R.string.refresh),
                    modifier = Modifier.padding(vertical = 5.dp),
                    style = MaterialTheme.typography.body1.copy(
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}