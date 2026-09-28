package com.emma.myapp.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emma.myapp.screens.dashboard.DashboardScreen
import com.emma.myapp.screens.login.LoginScreen
import com.emma.myapp.screens.onboarding.OnboardingScreen
import com.emma.myapp.screens.register.RegisterScreen
import com.emma.myapp.screens.splashscreen.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController= rememberNavController(),
    startDestination: String = ROUTE_SPLASH_SCREEN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_DASHBOARD_SCREEN) {
            DashboardScreen(navController)
        }
        composable(ROUTE_ONBOARDING_SCREEN) {
            OnboardingScreen(navController)
        }
    }
}
