package com.emma.myapp.ViewModel

import android.content.Context
import android.net.Uri
import androidx.navigation.NavHostController
import com.cloudinary.Url
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
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
    var uploadedPreset = "MyAppProduct"
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
            }catch (e: Exception){

            }
        }

    }
    //upload image to cloudinary function
    private fun uploadImageToCloudinary(context: Context, uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.readBytes()
            ?: throw Exception("Image read failed")
        val uploadPreset = ""
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                "image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(), fileBytes)
            )
            .addFormDataPart("upload_preset", uploadPreset)
            .build()
        val request = Request.Builder()
            .url(cloudinaryUrl)
            .post(requestBody)
            .build()
        val response = OkHttpClient().newCall(request).execute()
        if (!response.isSuccessful) throw Exception("Upload failed")
        val responseBody = response.body?.string()
        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")?.groupValues?.get(1)
        return secureUrl ?: throw Exception("Failed to get image URL")
    }
}