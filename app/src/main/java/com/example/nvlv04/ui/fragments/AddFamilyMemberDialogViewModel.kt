package com.example.nvlv04.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nvlv04.model.entity.User
import com.example.nvlv04.model.entity.familyMember

class AddFamilyMemberDialogViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var members:ArrayList<familyMember>
    init {
        members= ArrayList<familyMember>()
    }
    private var membersMutableLiveData= MutableLiveData<ArrayList<familyMember>>()
    val membersLiveData: LiveData<ArrayList<familyMember>>
        get() =membersMutableLiveData

    private var adduserApiMutableLiveData= MutableLiveData<User>()
    val adduserApiLiveData: LiveData<User>
        get() =adduserApiMutableLiveData
fun addMember(member:familyMember){
    membersMutableLiveData.value= membersMutableLiveData.value?.plus(member) as ArrayList<familyMember>?
}

}