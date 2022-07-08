package com.geico.hilttabapplication.repository

import com.geico.hilttabapplication.network.ApiInterface
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val api:ApiInterface){
    suspend fun getAllPosts() = api.getApiData()
}