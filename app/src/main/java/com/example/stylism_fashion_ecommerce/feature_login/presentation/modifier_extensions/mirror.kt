package com.example.stylism_fashion_ecommerce.feature_login.presentation.modifier_extensions

import android.util.LayoutDirection
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.core.text.layoutDirection
import java.util.*

@Stable
fun Modifier.mirror(): Modifier {
    return if (Locale.getDefault().layoutDirection == LayoutDirection.RTL) {
        this.scale(scaleX = -1f, scaleY = 1f)
    } else {
        this
    }
}