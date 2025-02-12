@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.hamoda.run.presntation.run_overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import com.hamoda.run.presentation.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.hamoda.run.presntation.run_overview.components.RunListItem

@Composable
fun RunOverviewScreenRoot(
    onStartRunClick: () -> Unit,
    viewModel: RunOverviewModel = koinViewModel()
) {
    RunOverviewScreen(
        state = viewModel.state,
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
    state: RunOverviewState,
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(horizontal = 16.dp),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = state.runs, key = { it.id }) {
                RunListItem(
                    runUi = it,
                    onDeleteClick = { onAction(RunOverviewAction.DeleteRun(it)) },
                    modifier = Modifier.animateItemPlacement()
                )
            }
        }
    }
}

@Preview
@Composable
private fun RunOverviewScreenPreview() {
    RunJourneyTheme {
        RunOverviewScreen(
            state = RunOverviewState(),
            onAction = {}
        )
    }
}