package com.example.nvlv04.model.remote

import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import retrofit2.Response

interface remoteRepo {
    suspend fun getApiUsers(name: String): Response<List<User>>
    suspend fun getApiAllUsers(): Response<List<User>>
    suspend fun addApiUser(user: User): Response<User>
    suspend fun getApiAppUser(national_id: String): Response<List<appUser>>
    suspend fun addApiAppUser(appUser: appUser): Response<User>

}