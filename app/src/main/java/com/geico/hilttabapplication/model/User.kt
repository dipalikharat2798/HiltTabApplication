package com.geico.hilttabapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")val id: Int=0,
    @ColumnInfo(name = "name")val name:String,
    @ColumnInfo(name = "lastname")val lastname:String,
    @ColumnInfo(name = "password")val passord:String
)
