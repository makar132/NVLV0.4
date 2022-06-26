package com.example.nvlv04.model.remote

import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import retrofit2.Response
import retrofit2.http.*

interface serviceApi {
    @GET("/makar132/test_rest_api_repo/users")
    suspend fun getApiUsers(
        @Query("name", encoded = false) name:String
    ):Response<List<User>>
    @GET("/makar132/test_rest_api_repo/appusers")
    suspend fun getApiAppUser(
        @Query("national_id", encoded = false) national_id:String
    ):Response<List<appUser>>
/**
 * check '/' before request url
 */
    @POST("/makar132/test_rest_api_repo/users")
    suspend fun addApiUser(
        @Body user: User
    ):Response<User>



}