package com.emma.myapp.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,

                ),
                actions = {
                    //IconButton(onClick = {  }) {
                      //  Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    //}
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", )
                    }
                    IconButton({}) {
                        Icon(Icons.Default.Person, contentDescription = "Person", )
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout", )
                    }
                }
            )
        },
       bottomBar = {
           NavigationBar(

           ) {
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
       },
        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, contentDescription = "Logout")
            }
       }
    ){ innerPadding ->
        //Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = CenterHorizontally
        )
        { Text("Welcome to the Dashboard",
            fontSize = 20.sp
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(rememberNavController())
}
//
//