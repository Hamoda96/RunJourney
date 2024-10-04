@file:OptIn(ExperimentalMaterial3Api::class)

package com.hamoda.run.presntation.run_overview

import com.hamoda.core.presentation.designsystem.components.AnalyticsIcon
import com.hamoda.core.presentation.designsystem.components.LogoIcon
import com.hamoda.core.presentation.designsystem.components.LogoutIcon
import com.hamoda.core.presentation.designsystem.components.RunIcon
import com.hamoda.core.presentation.designsystem.components.RunJourneyFloatingActionButton
import com.hamoda.core.presentation.designsystem.components.RunJourneyScaffold
import com.hamoda.core.presentation.designsystem.components.RunJourneyToolbar
import com.hamoda.core.presentation.designsystem.components.utils.DropDownItem
import com.hamoda.core.presentation.designsystem.RunJourneyTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import com.hamoda.run.presentation.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RunOverviewScreenRoot(
    onStartRunClick: () -> Unit,
    viewModel: RunOverviewModel = koinViewModel()
) {
    RunOverviewScreen(
        onAction = { action ->
            when (action) {
                RunOverviewAction.OnStartClick -> onStartRunClick()
                else -> {}
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun RunOverviewScreen(
    onAction: (RunOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    RunJourneyScaffold(
        topAppBar = {
            RunJourneyToolbar(
                showBackButton = false,
                title = stringResource(R.string.runjourney),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownItem(
                        title = stringResource(R.string.analytics),
                        icon = AnalyticsIcon
                    ),
                    DropDownItem(
                        title = stringResource(R.string.logout),
                        icon = LogoutIcon
                    )
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            RunJourneyFloatingActionButton(
                icon = RunIcon,
                onClick = { onAction(RunOverviewAction.OnStartClick) }
            )
        }
    ) { padding ->

    }
}

@Preview
@Composable
private fun RunOverviewScreenPreview() {
    RunJourneyTheme {
        RunOverviewScreen(
            onAction = {}
        )
    }
}