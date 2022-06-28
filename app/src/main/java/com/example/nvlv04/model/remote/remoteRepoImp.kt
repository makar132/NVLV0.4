package com.example.nvlv04.model.remote

import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import com.example.nvlv04.model.entity.familyMember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class remoteRepoImp(private val api: serviceApi) : remoteRepo {
    override suspend fun getApiUser(token: String) =
        withContext(Dispatchers.IO) {
            api.getApiUser(token)
        }



    override suspend fun getApiAllUsers(): Response<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun addApiUser(user: User): Response<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getApiAppUser(national_id: String): Response<List<appUser>> {
        TODO("Not yet implemented")
    }

    override suspend fun addApiAppUser(appUser: appUser): Response<User> {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(national_id: String , password:String)=
        withContext(Dispatchers.IO) {

            api.loginUser(loginRequest(national_id, password))
        }


}