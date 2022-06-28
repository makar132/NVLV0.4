package com.example.nvlv04.model.remote

import android.graphics.Bitmap
import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import retrofit2.Response
import retrofit2.http.*

interface serviceApi {
    @GET("/user")
    suspend fun getApiUser(
        @Header("Authorization") token: String
    ):Response<appUser>
    @POST("/picture")
    suspend fun setuserpicture(
        @Header("Content-Type") contentType: String,
        @Body picture: Bitmap,
    ):Response<Int>
    @GET("/picture")
    suspend fun getuserpicture(
        @Query("id") id: String
    ):Response<Bitmap>
    @POST("/login")
    suspend fun loginUser(
        @Body login_request:loginRequest

    ):Response<jsonModel>



}