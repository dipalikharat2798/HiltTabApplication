package com.geico.hilttabapplication.repository

import com.geico.hilttabapplication.data.AppDao
import com.geico.hilttabapplication.model.User
import javax.inject.Inject

class RoomRepository @Inject constructor(private val appDao: AppDao) {

    fun getRecords(): List<User>{
        return appDao.getRecords()
    }

    fun insertRecord(user: User){
        appDao.insertRecord(user)
    }
}