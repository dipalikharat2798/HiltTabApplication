package com.geico.hilttabapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.geico.hilttabapplication.model.User

@Dao
interface AppDao {

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getRecords(): List<User>

    @Insert
    fun insertRecord(user: User)
}