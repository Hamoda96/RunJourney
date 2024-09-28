package com.hamoda.auth.presentation.intro.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamoda.auth.presentation.R
import com.hamoda.auth.presentation.intro.IntroAction
import com.hamoda.core.presentation.designsystem.RunJourneyTheme
import com.hamoda.core.presentation.designsystem.components.GradientBackground
import com.hamoda.core.presentation.designsystem.components.RunJourneyActionButton
import com.hamoda.core.presentation.designsystem.components.RunJourneyOutlinedActionButton

@Composable
fun IntroScreenContentSection(modifier: Modifier = Modifier, onAction: (IntroAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(bottom = 48.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_to_runjourney),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.runjourney_description),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(32.dp))
        RunJourneyOutlinedActionButton(
            text = stringResource(R.string.sign_in),
            isLoading = false,
            onClick = { onAction(IntroAction.OnSignInClick) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        RunJourneyActionButton(
            text = stringResource(R.string.sign_up),
            isLoading = false,
            onClick = { onAction(IntroAction.OnSignUpClick) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun IntroScreenContentSectionPreview() {
    RunJourneyTheme {
        GradientBackground {
            IntroScreenContentSection(onAction = {})
        }
    }
}