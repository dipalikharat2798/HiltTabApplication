package com.geico.hilttabapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geico.hilttabapplication.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): AppDao
    companion object {
        private var dbINSTANCE: AppDatabase? = null

        fun getAppDb(context: Context): AppDatabase {
            if (dbINSTANCE == null) {
                dbINSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "MyDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return dbINSTANCE!!
        }
    }
}