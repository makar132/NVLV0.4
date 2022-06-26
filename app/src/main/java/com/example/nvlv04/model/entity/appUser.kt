package com.example.nvlv04.model.entity

data class appUser(
    val national_id: String,
    val first_name: String="",
    val last_name: String="",
    val email: String="",
    val password: String="",
    val phone: String="",
    val address: String="",
    val subscription: String="",
    val family:List<familyMember> = emptyList()
    )
