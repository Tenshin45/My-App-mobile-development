package com.emma.myapp.screens.UserDashboard


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emma.myapp.ViewModel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboard(navController: NavHostController) {
    val context = LocalContext.current
    val authViewModel = AuthViewModel(navController, context)
    Scaffold(
        topBar ={
            TopAppBar(
                title = { Text("User Dashboard") },
                actions = {
                    //IconButton(onClick = {  }) {
                    //  Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    //}
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings" )
                    }
                    IconButton({  }) {
                        Icon(Icons.Default.Person, contentDescription = "Person" )
                    }
                    IconButton(onClick = {authViewModel.signOutUser()}) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout" )
                    }
                }
            )
        },
        bottomBar = {
            //Bottom Navigation
            NavigationBar {
                //Bottom Navigation Items
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") }

                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    label = { Text("Search") },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    label = { Text("Dashboard") },
                    icon = { Icon(Icons.Default.Dashboard, contentDescription = "Dashboard") }
                )
            }
        }
    ) {
        innerPadding ->
        //Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = CenterHorizontally
        )
        {
            Text(
                "Welcome to the User Dashboard",
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun UserDashboardPreview() {
  UserDashboard(rememberNavController())
}
//
//