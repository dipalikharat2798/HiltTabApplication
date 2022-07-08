package com.geico.hilttabapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geico.hilttabapplication.model.PostDataItem
import com.geico.hilttabapplication.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(private val repository: NetworkRepository) :
    ViewModel() {
    private val _response = MutableLiveData<List<PostDataItem>>()

    val responsePosts: LiveData<List<PostDataItem>>
        get() = _response

    init {
        getAllPosts()
    }

    private fun getAllPosts() = viewModelScope.launch {
        repository.getAllPosts().enqueue(object : Callback<List<PostDataItem>?> {
            override fun onResponse(
                call: Call<List<PostDataItem>?>,
                response: Response<List<PostDataItem>?>
            ) {
                _response.postValue(response.body())
            }

            override fun onFailure(call: Call<List<PostDataItem>?>, t: Throwable) {
                Log.d(
                    "your tag",
                    "getAllImages Error: "
                )
            }
        })
    }


}