package com.example.nvlv04.model

import android.content.Context
import android.content.SharedPreferences
import com.example.nvlv04.model.entity.familyMember

class PrefManager(context: Context?) {

    // Shared pref mode
    val PRIVATE_MODE = 0

    // Sharedpref file name
    private val PREF_NAME = "SharedPreferences"

    private val IS_LOGIN = "is_login"
    private val family_list:List<familyMember> = emptyList()

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setLoggin(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }
    fun setOnboarding() {
        editor?.putBoolean("Onboard", true)
        editor?.commit()
    }
    fun setUsername(username: String?) {
        editor?.putString("username", username)
        editor?.commit()
    }
    fun setPassword(password: String?) {
        editor?.putString("password", password)

        editor?.commit()
    }
    fun setFamilyMemberimageid(id: Int) {
        editor?.putInt("FamilyMemberimageid", id)

        editor?.commit()
    }
    fun setFamilyMemberfirstname(firstname: String?) {
        editor?.putString("FamilyMemberfirstname", firstname)

        editor?.commit()
    }
    fun setFamilyMemberlastname(lastname: String?) {
        editor?.putString("FamilyMemberlastname", lastname)

        editor?.commit()
    }
    fun setFamilyMembermedicalrecord(medicalrecord: String?) {
        editor?.putString("FamilyMembermedicalrecord", medicalrecord)
        editor?.commit()
    }

    fun isLogin(): Boolean? {
        return pref?.getBoolean(IS_LOGIN, false)
    }
    fun Onboard(): Boolean? {
        return pref?.getBoolean("Onboard", false)
    }
    fun getUsername(): String? {
        return pref?.getString("username", "admin")
    }
    fun getPassword(): String? {
        return pref?.getString("password", "admin")
    }
    fun getFamilyMemberimageid(): Int? {
        return pref?.getInt("FamilyMemberimageid", 0)
    }
    fun getFamilyMemberfirstname(): String? {
        return pref?.getString("FamilyMemberfirstname", "")
    }
    fun getFamilyMemberlastname(): String? {
        return pref?.getString("FamilyMemberlastname", "")
    }
    fun getFamilyMembermedicalrecord(): String? {
        return pref?.getString("FamilyMembermedicalrecord", "no medical record")
    }
    fun reserfamilyMember(){
        setFamilyMemberfirstname(null)
        setFamilyMemberlastname(null)
        setFamilyMembermedicalrecord(null)
        setFamilyMemberimageid(0)
    }

    fun removeData() {
        editor?.clear()
        editor?.putBoolean("Onboard", Onboard()!!)
        editor?.commit()
    }
}