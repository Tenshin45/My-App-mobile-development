package com.emma.myapp.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person

import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emma.myapp.ViewModel.AuthViewModel
import com.emma.myapp.screens.navigation.ROUTE_ADD_PRODUCT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    val context= LocalContext.current
    val myAuth= AuthViewModel(navController,context)
    Scaffold(

        //topbar
        topBar = {
            TopAppBar(
                title={Text("Dashboard")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings,
                            contentDescription = "icon")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Person,
                            contentDescription = "icon")
                    }
                    IconButton(onClick = { myAuth.signOutUser()}) {
                        Icon(
                            Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "icon")
                    }
                }
            )
        },
        //bottom bar
        bottomBar = {
            NavigationBar(
                containerColor = Color.LightGray
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Home,
                            contentDescription = "home icon")
                    },
                    label={Text("Home")}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Settings,
                            contentDescription = "settings icon")
                    },
                    label={Text("Settings")}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Person,
                            contentDescription = "person icon")
                    },
                    label={Text("Profile")}
                )
            }
        },
        //floating action button
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add,
                    contentDescription = "add icon")
            }
        }

    )

    {
            innerpadding ->
        //column
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .background(color=Color.White)
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            var username by remember { mutableStateOf("") }
            LaunchedEffect(key1 = true) {
                myAuth.getCurrentUserName { name ->
                    username = name
                }
            }
            Text("welcome $username !",
                fontSize = 28.sp,
                color=Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            //row
            Row {
                DashboardCard(
                    title = "Add product",
                    background = Color.Yellow,
                    onClick = {navController.navigate(ROUTE_ADD_PRODUCT)}
                )
                DashboardCard(
                    title="Profile",
                    background = Color.DarkGray,
                    onClick = {}
                )
            }
            Row {
                DashboardCard(
                    title = "Product list",
                    background = Color.Gray,
                    onClick = {navController.navigate("view_product")})
                DashboardCard(
                    title = "User-Dashboard",
                    background = Color.LightGray,
                    onClick = {})
            }


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

//dashboard card
@Composable
fun DashboardCard(
    title: String,
    background: Color,
    onClick:()-> Unit)
{
    Card(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(8.dp)
            .clickable{onClick()},
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color=Color.Black)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DashboardCardPreview(){
    DashboardCard(
        title = "My App",
        background = Color.DarkGray,
        onClick = {}
    )
}