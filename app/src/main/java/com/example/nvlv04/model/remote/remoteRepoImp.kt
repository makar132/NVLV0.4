package com.example.nvlv04.model.remote

import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import com.example.nvlv04.model.entity.familyMember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class remoteRepoImp(private val api: serviceApi) : remoteRepo {
    override suspend fun getApiUsers(name: String) =
        withContext(Dispatchers.IO) {
            api.getApiUsers(name)
        }

    override suspend fun getApiAllUsers() =
        withContext(Dispatchers.IO) {
        api.getApiAllUsers()
    }

    override suspend fun addApiUser(user: User) = withContext(Dispatchers.IO) {

        api.addApiUser(user)
    }

    override suspend fun getApiAppUser(national_id: String): Response<List<appUser>> =
        withContext(Dispatchers.IO) {
            api.getApiAppUser(national_id)
        }

    override suspend fun addApiAppUser(appUser: appUser): Response<User> {
        TODO("Not yet implemented")
    }


}