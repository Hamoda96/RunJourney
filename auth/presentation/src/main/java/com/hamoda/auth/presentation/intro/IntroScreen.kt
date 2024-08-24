package com.hamoda.auth.presentation.intro

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hamoda.auth.presentation.intro.component.IntroScreenContentSection
import com.hamoda.auth.presentation.intro.component.IntroScreenLogoSection
import com.hamoda.core.presentation.designsystem.RunJourneyTheme
import com.hamoda.core.presentation.designsystem.components.GradientBackground


@Composable
fun IntroScreenRoot(onSignInClick: () -> Unit, onSignUpClick: () -> Unit) {
    IntroScreen(
        onAction = { action ->
            when (action) {
                IntroAction.OnSignInClick -> onSignInClick()
                IntroAction.OnSignUpClick -> onSignUpClick()
            }
        },
    )
}

@Composable
fun IntroScreen(onAction: (IntroAction) -> Unit) {
    GradientBackground {
        IntroScreenLogoSection()

        IntroScreenContentSection(onAction = onAction)
    }
}

@Preview
@Composable
private fun InTroScreenPreview() {
    RunJourneyTheme {
        IntroScreen(onAction = {})
    }
}