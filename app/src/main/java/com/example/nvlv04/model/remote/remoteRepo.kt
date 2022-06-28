package com.example.nvlv04.model.remote

import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body

interface remoteRepo {
    suspend fun getApiUser(token: String): Response<appUser>
    suspend fun getApiAllUsers(): Response<List<User>>
    suspend fun addApiUser(user: User): Response<User>
    suspend fun getApiAppUser(national_id: String): Response<List<appUser>>
    suspend fun addApiAppUser(appUser: appUser): Response<User>
    suspend fun loginUser(national_id: String , password:String): Response<jsonModel>


}