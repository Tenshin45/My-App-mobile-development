package com.emma.myapp.screens.product

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            // Top app bar content
            TopAppBar(
                title = { Text("Update Product") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White
                )
            )
        },
    ) {
        innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(16.dp)
        ){
            Text("Update Product",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color=Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Add your update product form fields and logic here
            val productName = remember { mutableStateOf("") }
            val productDescription = remember { mutableStateOf("") }
            val productPrice = remember { mutableStateOf("") }
            val productImageUrl = remember { mutableStateOf("") }
            val imagePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                productImageUrl.value = uri.toString()
            }
            // Product name input field
            OutlinedTextField(
                value = productName.value,
                onValueChange = { productName.value = it },
                label = { Text("Product Name") }
            )
            // Product description input field
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = productDescription.value,
                onValueChange = { productDescription.value = it },
                label = { Text("Product Description") }
            )
            // Product price input field
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = productPrice.value,
                onValueChange = { productPrice.value = it },
                label = { Text("Product Price") }
            )
            // Product image URL input field
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = productImageUrl.value,
                onValueChange = { productImageUrl.value = it },
                label = { Text("Product Image URL") }
            )
            // Image picker button
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton (onClick = { imagePickerLauncher.launch("image/*") }){
                Text("Pick an image")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Add your update product button and logic here
            Button(
                onClick = { /* Handle update product logic here */ },
                modifier = Modifier
                    .fillMaxSize(),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text("Update Product")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun UpdateProductScreenPreview() {

}
//