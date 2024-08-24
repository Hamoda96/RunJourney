package com.hamoda.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.hamoda.core.presentation.designsystem.components.Typography

val DarkColorScheme = darkColorScheme(
    primary = RunJourneyGreen,
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