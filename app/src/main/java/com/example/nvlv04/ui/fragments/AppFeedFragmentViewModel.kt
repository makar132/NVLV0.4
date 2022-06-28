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
        user= appUser()
    }
    private var appuserApiMutableLiveData= MutableLiveData<appUser>()
    val appuserApiLiveData: LiveData<appUser>
        get() =appuserApiMutableLiveData
    fun getuser(token:String)=viewModelScope.launch{
        val result=remoteRepoImp.getApiUser("Bearer "+token)
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