package com.example.nvlv04.ui.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.remote.remoteRepoImp
import com.example.nvlv04.model.remote.retroBuilder
import com.example.nvlv04.model.remote.serviceApi
import kotlinx.coroutines.launch

class UserListViewModel(app:Application) : AndroidViewModel(app) {
    // TODO: Implement the ViewModel
    private var remoteRepoImp:remoteRepoImp
    var u:List<User>
    init {

        remoteRepoImp= remoteRepoImp(retroBuilder.getRetroBuilder().create(serviceApi::class.java))
        u= emptyList()
    }
    private var userApiMutableLiveData=MutableLiveData<List<User>>()
    val userApiLiveData:LiveData<List<User>>
        get() =userApiMutableLiveData

    private var adduserApiMutableLiveData=MutableLiveData<User>()
    val adduserApiLiveData:LiveData<User>
        get() =adduserApiMutableLiveData



    fun getUsersApi(name:String)=viewModelScope.launch{
        u= listOf(User(0,"a","None"))
        val result=remoteRepoImp.getApiUsers(name)
        u= listOf(User(0,"aa","None"))
        u= listOf(User(0,"aad","None"))
        if(result.isSuccessful){
            u= listOf(User(0,"b","None"))
            if (result.body()!=null)
            {
                u= listOf(User(0,"c","None"))
                userApiMutableLiveData.postValue(result.body())
            }
        }
        else {
            u= listOf(User(0,"d","None"))
            Log.i("error",result.message())
        }
    }
    fun addUserApi(user: User)=viewModelScope.launch {
        val result=remoteRepoImp.addApiUser(user)
        u= listOf(User(0,"a","None"))
        if(result.isSuccessful){
            u= listOf(User(0,"b","None"))
            if (result.body()!=null)
            {
                u= listOf(User(0,"c","None"))
                adduserApiMutableLiveData.postValue(result.body())
            }
        }
        else {
            u= listOf(User(0,"d","None"))
            Log.i("error1","${result.isSuccessful}")
            Log.i("error2","${result.body()}")
            Log.i("error3","${result.errorBody()}")
            Log.i("error4","${result.raw()}")
            Log.i("error5",result.message())
            Log.i("error6","${result.code()}")
            Log.i("error6","${result.toString()}")



        }
    }
}