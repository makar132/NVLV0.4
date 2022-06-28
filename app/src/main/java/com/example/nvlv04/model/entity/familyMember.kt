package com.example.nvlv04.model.entity

import androidx.appcompat.widget.AppCompatImageView

data class familyMember(
    var photo: AppCompatImageView,
    var firstname: String?,
    var lastname: String?,
    var medical_history: String
)
