package com.emma.myapp.screens.product

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.emma.myapp.R
import com.emma.myapp.ViewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductScreen(navController: NavHostController, productId: String) {
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
                .fillMaxSize()
                .padding(innerpadding)
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally
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
            var imageUri by remember { mutableStateOf<Uri?>(null) }
            var imageUrl by remember { mutableStateOf<String?>(null) }
            val imagePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
               if (uri!=null)imageUri=uri
            }
            val context= LocalContext.current
            val productViewModel= ProductViewModel(navController, context)
            LaunchedEffect(productId) {
                productViewModel.databaseReference.child(productId).get().addOnSuccessListener {
                        snapshot ->
                    val product=snapshot.getValue(com.emma.myapp.screens.models.Products::class.java)
                    product ?. let{
                        productName.value=it.name
                        productDescription.value=it.description
                        productPrice.value=it.price
                        productImageUrl.value=it.imageUrl
                    }
                }
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
                label = { Text("Product Price") },
                // Keyboard options to set the
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            // Product image URL input field
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = productImageUrl.value,
                onValueChange = { productImageUrl.value = it },
                label = { Text("Product Image URL") }
            )
            // Product image preview
            Spacer(modifier = Modifier.height(16.dp))
            val imageSource:Any?=imageUri ?:imageUrl
            if (imageSource !=null){
                AsyncImage(
                    model=imageSource,
                    contentDescription = "product",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop

                )
            }
            Spacer(modifier= Modifier.height(20.dp))
            //image picker
            Button(onClick ={imagePickerLauncher.launch("image/*") },
                modifier= Modifier.fillMaxWidth(),) {
                Text("UPDATE IMAGE")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Add your update product button and logic here
            Button(
                onClick = { /* Handle update product logic here */ },
                modifier = Modifier
                    .fillMaxWidth(),
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
    UpdateProductScreen(rememberNavController(), productId = "1")
}
//