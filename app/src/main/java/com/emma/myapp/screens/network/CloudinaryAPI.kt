package com.emma.myapp.screens.network

import com.emma.myapp.screens.models.CloudinaryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CloudinaryAPI {
    @Multipart
    @POST("v1_1/prf7lupw/image/upload")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("upload_preset") uploadPreset: RequestBody
    ): Response<CloudinaryResponse>

}