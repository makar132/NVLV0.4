package com.example.nvlv04.ui.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import com.example.nvlv04.model.remote.remoteRepoImp
import com.example.nvlv04.model.remote.retroBuilder
import com.example.nvlv04.model.remote.serviceApi
import kotlinx.coroutines.launch

class AppFeedFragmentViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var remoteRepoImp: remoteRepoImp
    var user: appUser
    init {
        remoteRepoImp= remoteRepoImp(retroBuilder.getRetroBuilder().create(serviceApi::class.java))
        user= appUser("-1")
    }
    private var appuserApiMutableLiveData= MutableLiveData<List<User>>()
    val appuserApiLiveData: LiveData<List<User>>
        get() =appuserApiMutableLiveData
    fun getfeed()=viewModelScope.launch{
        val result=remoteRepoImp.getApiAllUsers()
        if(result.isSuccessful){
            if (result.body()!=null)
            {
                appuserApiMutableLiveData.postValue(result.body())
            }
        }
        else {
            Log.i("error1",result.message())
        }
    }
}