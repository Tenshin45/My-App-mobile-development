package com.emma.myapp.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emma.myapp.screens.Cart.CartScreen
import com.emma.myapp.screens.OderScreen.OrdersScreen
import com.emma.myapp.screens.Settings.SettingsScreen
import com.emma.myapp.screens.UserDashboard.userDashboard
import com.emma.myapp.screens.dashboard.DashboardScreen
import com.emma.myapp.screens.demo.IntentScreen
import com.emma.myapp.screens.Profile.ProfileScreen
import com.emma.myapp.screens.login.LoginScreen
import com.emma.myapp.screens.onboarding.OnboardingScreen
import com.emma.myapp.screens.product.AddProductScreen
import com.emma.myapp.screens.product.ProductListScreen
import com.emma.myapp.screens.product.UpdateProductScreen
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
        composable(ROUTE_USER_DASHBOARD) {
            userDashboard(navController)
        }
        composable(ROUTE_ADD_PRODUCT){
            AddProductScreen(navController)
        }
        composable(ROUTE_VIEW_PRODUCT){
            ProductListScreen(navController)
        }
        composable("$ROUTE_UPDATE_PRODUCT/{productId}"){ backStackEntry ->
            val productId=backStackEntry.arguments?.getString("productId")!!
            UpdateProductScreen(navController,productId)
        }
        composable(ROUTE_INTENT_SCREEN){
            IntentScreen(navController)
        }
        composable(ROUTE_PROFILE_SCREEN){
            ProfileScreen(navController)
        }
        composable(ROUTE_CART_SCREEN){
            CartScreen(navController)
        }
        composable(ROUTE_ORDER_SCREEN){
            OrdersScreen(navController)
        }
        composable(ROUTE_SETTINGS_SCREEN){
            SettingsScreen(navController)
        }
    }

}
