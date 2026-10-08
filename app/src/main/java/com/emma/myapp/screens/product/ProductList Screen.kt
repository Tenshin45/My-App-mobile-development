package com.emma.myapp.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.emma.myapp.ViewModel.ProductViewModel
import com.emma.myapp.screens.models.Products
import com.emma.myapp.screens.navigation.ROUTE_ADD_PRODUCT
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavHostController
){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title =  {Text("PRODUCT LIST",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp)},
                colors= TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(ROUTE_ADD_PRODUCT)},

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add icon",
                    tint = Color.DarkGray
                )
            }
        }

    ) {
           innerpadding ->
        val products = remember {
            mutableStateListOf<Products>()
       }
        val context = LocalContext.current
        val productViewModel = ProductViewModel(navController, context)
        LaunchedEffect(key1 = products){
            productViewModel.allProducts(products)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            items(products) { item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation =6.dp)
                ) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = "product image",
                        contentScale =  ContentScale.Crop,
                        modifier=Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                    )
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(CenterHorizontally)
                    ) {
                        Text(
                            text = item.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = item.description,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Price: KSH${item.price}",
                            fontSize = 18.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(CenterHorizontally)
                    ) {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Yellow,
                                contentColor = Color.White
                            )
                        ){
                            Text("Update")
                        }
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Green,
                                contentColor = Color.White
                            )
                        ){
                            Text("Buy")
                        }
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ){
                            Text("Delete")
                        }
                    }
                }


            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview(){
    ProductListScreen(rememberNavController())
}