package com.hamoda.runjourney.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hamoda.auth.presentation.intro.IntroScreenRoot
import com.hamoda.auth.presentation.login.LoginScreenRoot
import com.hamoda.auth.presentation.register.RegisterScreenRoot
import com.hamoda.run.presntation.active_run.ActiveRunScreenRoot
import com.hamoda.run.presntation.active_run.service.ActiveRunService
import com.hamoda.run.presntation.run_overview.RunOverviewScreenRoot
import com.hamoda.runjourney.MainActivity

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "run" else "auth"
    ) {
        authGraph(navController = navController)
        runGraph(navController = navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "auth"
    ) {
        composable(route = "intro") {
            IntroScreenRoot(
                onSignInClick = {
                    navController.navigate(route = "login")
                },
                onSignUpClick = {
                    navController.navigate(route = "register")
                }
            )
        }

        composable(route = "register") {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate(route = "login") {
                        popUpTo(route = "register") {
                            // inclusive = true: Removes the "auth" destination itself from the back stack.
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate(route = "login")
                }
            )
        }

        composable("login") {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate(route = "run") {
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navController.navigate(route = "register") {
                        popUpTo(route = "login") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.runGraph(navController: NavHostController) {
    navigation(
        startDestination = "run_overview",
        route = "run"
    ) {
        composable(route = "run_overview") {
            RunOverviewScreenRoot(
                onStartRunClick = {
                    navController.navigate(route = "active_run")
                }
            )
        }

        composable(
            route = "active_run",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "runjourney://active_run"
                }
            )
        ) {
            val context = LocalContext.current
            ActiveRunScreenRoot(
                onServiceToggle = { shouldServiceRun ->
                    if (shouldServiceRun) {
                        context.startService(
                            ActiveRunService.createStartIntent(
                                context = context,
                                activityClass = MainActivity::class.java
                            )
                        )
                    } else {
                        ActiveRunService.createStopIntent(context = context)
                    }
                },
                onFinish = {
                    navController.navigateUp()
                },
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}