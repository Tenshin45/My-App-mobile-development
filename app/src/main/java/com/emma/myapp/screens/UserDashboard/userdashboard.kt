package com.emma.myapp.screens.UserDashboard


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.base.Default
import coil.compose.AsyncImage
import com.emma.myapp.R
import com.emma.myapp.ViewModel.AuthViewModel
import com.emma.myapp.ViewModel.ProductViewModel
import com.emma.myapp.screens.models.Products
import com.emma.myapp.screens.navigation.ROUTE_ORDER_SCREEN

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun userDashboard(navController: NavHostController) {
    val context = LocalContext.current
    val authViewModel = AuthViewModel(navController, context)
    val productViewModel = ProductViewModel(navController, context)
    var username by remember { mutableStateOf("") }
    val products = remember { mutableStateListOf<Products>() }
    //LaunchedEffect to fetch username and products
    LaunchedEffect(Unit) {
        authViewModel.getCurrentUserName { name ->
            username = name
        }
        productViewModel.allProducts(products)
    }
    Scaffold(
        topBar ={
            TopAppBar(
                title = { Text("User Dashboard") },
                actions = {
                    //IconButton(onClick = {  }) {
                    //  Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    //}
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings" )
                    }
                    IconButton(onClick = { navController.navigate(ROUTE_ORDER_SCREEN) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ReceiptLong,
                            contentDescription = "My Orders"
                        )
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
                    onClick = { navController.navigate("profile") },
                    label = { Text("Profile") },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") }

                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("cart") },
                    label = { Text("Cart") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("dashboard") },
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
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally
        )
        {

            Text(
                "Welcome !",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))
            var searchText by remember { mutableStateOf(TextFieldValue()) }
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                leadingIcon ={Icon(imageVector = Icons.Default.Search, contentDescription = "Search")}
            )
            Spacer(modifier =  Modifier.padding(16.dp))
            Text(
                text="Featured Product",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ){
                items(products){
                        product ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation =6.dp),
                    ) {
                        AsyncImage(
                            model = product.imageUrl,
                            contentDescription = "product image",
                            contentScale =  ContentScale.Crop,
                            modifier=Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                        )
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = product.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = product.description,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "Price: KSH${product.price}",
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun UserDashboardPreview() {
  userDashboard(rememberNavController())
}
//
//