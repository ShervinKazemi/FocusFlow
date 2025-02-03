package com.example.focusflow.presentation.feature.task.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.focusflow.R

@Composable
fun AnimationComponent() {
    val isDarkTheme = isSystemInDarkTheme()
    val animation = if (isDarkTheme) R.raw.no_data_dark else R.raw.no_data_light

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animation))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }

}