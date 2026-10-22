package com.emma.myapp.screens.network

import com.emma.myapp.screens.models.AccessTokenResponse
import com.emma.myapp.screens.models.STKPushRequest
import com.emma.myapp.screens.models.STKPushResponse
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MpesaAPI {
    @GET("oauth/v1/generate?grant_type=client_credentials")
    suspend fun getAccessToken(
        @Header("Authorization") authHeader: String
    ): Response<AccessTokenResponse>

    @POST("mpesa/stkpush/v1/processrequest")
    suspend fun sendSTKPush(
        @Header("Authorization") tokenHeader: String,
        @Body stkPushRequest: STKPushRequest
    ): Response<STKPushResponse>
}