package com.hamoda.core.presentation.designsystem.components

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = RunJourneyGray,
    background = RunJourneyBlack,
    surface = RunJourneyDarkGray,
    secondary = RunJourneyWhite,
    tertiary = RunJourneyWhite,
    primaryContainer = RunJourneyGreen30,
    onPrimary = RunJourneyBlack,
    onBackground = RunJourneyWhite,
    onSurface = RunJourneyWhite,
    onSurfaceVariant = RunJourneyGray,
    error = RunJourneyDarkRed,
    errorContainer = RunJourneyDarkRed5
)

@Composable
fun RunJourneyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}