package com.example.nvlv04.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retroBuilder {
    companion object{
        private const val BaseURL="https://my-json-server.typicode.com"
        fun getRetroBuilder():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}