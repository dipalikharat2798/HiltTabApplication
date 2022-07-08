package com.geico.hilttabapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geico.hilttabapplication.repository.RoomRepository
import com.geico.hilttabapplication.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RoomRepository):ViewModel() {

    lateinit var userData:MutableLiveData<List<User>>

    init {
        userData= MutableLiveData()
    }

    fun getRecords():MutableLiveData<List<User>>{
        return userData
    }

    fun loadRecords(){
        val list = repository.getRecords()

        userData.postValue(list)
    }

    fun insertRecord(user: User){
        repository.insertRecord(user)
        loadRecords()
    }
}