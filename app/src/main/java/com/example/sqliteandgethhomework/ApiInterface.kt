package com.example.sqliteandgethhomework

import com.example.sqliteandgethhomework.Model.MyDataItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("marvel")
    fun getData(): Call<List<MyDataItem>>

    object NewService {
        val retrofitBulder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.simplifiedcoding.net/demos/")
            .build()
            .create(ApiInterface::class.java)
    }
}