package com.example.nvlv04.model.remote

import com.google.gson.annotations.SerializedName

data class jsonModel(
    @SerializedName("token")
    var token: String? = null
)
