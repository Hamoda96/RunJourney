package com.hamoda.core.presentation.designsystem.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamoda.core.presentation.designsystem.RunJourneyTheme

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    hasToolbar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthPx = with(density) {
        configuration.screenWidthDp.dp.roundToPx()
    }

    val smallDimension = minOf(
        configuration.screenWidthDp.dp,
        configuration.screenHeightDp.dp
    )

    val smallDimensionPx = with(density) {
        smallDimension.roundToPx()
    }

    val primaryColor = MaterialTheme.colorScheme.primary
    val isAtLeastAndroid12 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CircularBlurBackground(
            modifier = modifier,
            isAtLeastAndroid12 = isAtLeastAndroid12,
            screenWidthPx = screenWidthPx,
            smallDimensionPx = smallDimensionPx,
            primaryColor = primaryColor,
            smallDimension = smallDimension
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .HasToolbar(hasToolbar = hasToolbar)
        ) {
            content()
        }
    }
}

@Composable
private fun CircularBlurBackground(
    modifier: Modifier,
    isAtLeastAndroid12: Boolean,
    screenWidthPx: Int,
    smallDimensionPx: Int,
    primaryColor: Color,
    smallDimension: androidx.compose.ui.unit.Dp
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .then(
                if (isAtLeastAndroid12) {
                    Modifier.blur(smallDimension / 3f)
                } else Modifier
            )
            .CircularBackground(
                isAtLeastAndroid12 = isAtLeastAndroid12,
                primaryColor = primaryColor,
                smallDimensionPx = smallDimensionPx,
                screenWidthPx = screenWidthPx
            )
    )
}

@Composable
private fun Modifier.CircularBackground(
    isAtLeastAndroid12: Boolean,
    primaryColor: Color,
    smallDimensionPx: Int,
    screenWidthPx: Int
): Modifier {
    return this
        .background(
            brush = Brush.radialGradient(
                colors = listOf(
                    if (isAtLeastAndroid12) primaryColor else primaryColor.copy(alpha = 0.3f),
                    MaterialTheme.colorScheme.background
                ),
                center = Offset(
                    x = screenWidthPx / 2f,
                    y = -100f
                ),
                radius = smallDimensionPx / 2f
            )
        )
}

@Composable
fun Modifier.HasToolbar(hasToolbar: Boolean): Modifier {
    return this
        .then(
            if (hasToolbar) {
                Modifier
            } else {
                Modifier.systemBarsPadding()
            }
        )
}

@Preview
@Composable
private fun GradientBackgroundPreview() {
    RunJourneyTheme {
        GradientBackground(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}