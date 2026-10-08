package com.emma.myapp.ViewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import coil.disk.DiskCache
import com.cloudinary.Url
import com.emma.myapp.screens.models.Products
import com.emma.myapp.screens.navigation.ROUTE_VIEW_PRODUCT
import com.emma.myapp.screens.network.CloudinaryAPI
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream

class ProductViewModel(val navController: NavHostController, val context: Context) {
    init {
        try {
            if (FirebaseApp.getApps(context).isEmpty()) {
                FirebaseApp.initializeApp(context)
            }
        } catch (_: Exception) {
        }
    }
    var cloudinaryUrl = "https://api.cloudinary.com/v1_1/prf7lupw/upload/"
    var uploadPreset = "MyAppProduct"
    val databaseReference by lazy { FirebaseDatabase.getInstance().getReference("products") }
    fun addProduct(name: String, price: String, description: String, imageUri: Uri?){
        val ref = databaseReference.push()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val imageUrl = if (imageUri != null){
                    uploadImageToCloudinary(context=context,uri= imageUri)
                }else{

                }
                val productData =mapOf(
                    "productId" to ref.key,
                    "name" to name,
                    "price" to price,
                    "description" to description,
                    "imageUrl" to imageUrl,
                    "userId" to userId

                )
                ref.setValue(productData).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(context,"product added successfully", Toast.LENGTH_LONG).show()
                        //navigate to product list
                        navController.navigate(ROUTE_VIEW_PRODUCT)

                    }
                    else{
                        Toast.makeText(context,"Error: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }catch (e: Exception){
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "Upload failed ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    //upload image to cloudinary function
    private suspend fun uploadImageToCloudinary(context: Context, uri: Uri): String {
        val inputStream = context.contentResolver.openInputStream(uri) ?: throw Exception("Image read failed")
        // Convert image to bytes
        val fileBytes = inputStream.readBytes()
        // Create image request body
        val requestBody = fileBytes.toRequestBody("image/*".toMediaType())
        // Create multipart file
        val filePart = MultipartBody.Part.createFormData("file", "image.jpg", requestBody)
        // Create upload preset
        val preset = uploadPreset.toRequestBody("text/plain".toMediaType())
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.cloudinary.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Create Cloudinary API
        val cloudinaryAPI = retrofit.create(
            CloudinaryAPI::class.java
        )
        // Make the Cloudinary API call
        val response = cloudinaryAPI.uploadImage(filePart, preset)
        // Check response
        if (response.isSuccessful) {
            // Get Cloudinary image URL
            return response.body()?.secure_url
                ?: throw Exception("Image URL not found")
        } else {
            throw Exception(
                "Cloudinary upload failed: ${response.message()}"
            )
        }
    }
    //r-READ products from db
    //fetch all products from realtime database
    fun allProducts(products: SnapshotStateList<Products>){
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                products.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Products::class.java)
                    products.add(value!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    //u-update
    //update existing product in firebase
    fun updateProduct(){

    }
    //d-delete
    //delete  product in realtime database
    fun deleteProduct(){

    }


}