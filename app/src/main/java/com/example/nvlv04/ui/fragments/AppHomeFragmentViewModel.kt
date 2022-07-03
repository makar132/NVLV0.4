package com.example.nvlv04.ui.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import com.example.nvlv04.model.entity.familyMember
import com.example.nvlv04.model.remote.FamilyMember
import com.example.nvlv04.model.remote.remoteRepoImp
import com.example.nvlv04.model.remote.retroBuilder
import com.example.nvlv04.model.remote.serviceApi
import kotlinx.coroutines.launch

class AppHomeFragmentViewModel : ViewModel() {
    private var remoteRepoImp: remoteRepoImp
    var user:appUser
    init {
        remoteRepoImp= remoteRepoImp(retroBuilder.getRetroBuilder().create(serviceApi::class.java))
        user= appUser(-1)
    }
    private var appuserApiMutableLiveData= MutableLiveData<appUser>()
    val appuserApiLiveData: LiveData<appUser>
        get() =appuserApiMutableLiveData
    private var familyMutableLiveData= MutableLiveData<List<FamilyMember>>()
    val familyLiveData: LiveData<List<FamilyMember>>
        get() =familyMutableLiveData
    fun getAppUserdata(Jwt:String)=viewModelScope.launch{
        val result=remoteRepoImp.getApiUser("Bearer "+Jwt)
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
    fun getfamily(Jwt:String)=viewModelScope.launch{
        val result=remoteRepoImp.getFamily("Bearer "+Jwt)
        if(result.isSuccessful){
            if (result.body()!=null)
            {
                familyMutableLiveData.postValue(result.body()?.family_members)
            }
        }
        else {
            Log.i("error1",result.message())
        }
    }



}