package com.hamoda.runjourney.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hamoda.auth.presentation.intro.IntroScreenRoot
import com.hamoda.auth.presentation.register.RegisterScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        authGraph(navController = navController)
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
    }
}