package com.geico.hilttabapplication.network

import com.geico.hilttabapplication.model.PostDataItem
import retrofit2.Call
import retrofit2.http.GET

 interface ApiInterface {

    @GET("posts")
    fun getApiData():Call<List<PostDataItem>>
}