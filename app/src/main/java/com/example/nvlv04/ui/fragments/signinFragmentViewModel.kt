package com.example.nvlv04.ui.fragments

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nvlv04.model.PrefManager
import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.appUser
import com.example.nvlv04.model.remote.*
import com.qamar.curvedbottomnaviagtion.log
import kotlinx.coroutines.launch

class signinFragmentViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var remoteRepoImp: remoteRepoImp
    lateinit var prefManager: PrefManager
    var user: appUser

    init {
        remoteRepoImp = remoteRepoImp(retroBuilder.getRetroBuilder().create(serviceApi::class.java))
        user = appUser()
    }
    private var jwtApiMutableLiveData = MutableLiveData<appUser>()
    val jwtApiLiveData: LiveData<appUser>
        get() = jwtApiMutableLiveData
    private var appuserApiMutableLiveData = MutableLiveData<appUser>()
    val appuserApiLiveData: LiveData<appUser>
        get() = appuserApiMutableLiveData

    fun loginuser(national_id: String, password: String) = viewModelScope.launch {
        val result = remoteRepoImp.loginUser(national_id, password)
        if (result.isSuccessful) {
            Log.v("response", result.body()?.token!!.toString())
            if (result.body() != null) {
                log(result.body()?.token!!.toString())

                val jsonmodel = result.body()
                val getuser = remoteRepoImp.getApiUser("Bearer "+jsonmodel?.token!!)
                if (getuser.isSuccessful) {
                    if (getuser.body() != null) {
                        appuserApiMutableLiveData.postValue(getuser.body())
                    }
                    else{
                        Log.v("response", "error")
                    }
                }

            } else {
                Log.d("error", "error")
            }
        }

    }
}

