package com.himaya.ass_01.API

import com.himaya.ass_01.model.data
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitAPIHandler {
    @GET("POST")
    fun getdata(): Call<List<data>>

    companion object{
        val API_URL = "https://jsonplaceholder.typicode.com/"

        fun create():RetrofitAPIHandler{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(GsonConverterFactory.create())
                .baseUrl(API_URL)
                .build()
            return retrofit.create(RetrofitAPIHandler::class.java)
        }
    }
}