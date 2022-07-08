package com.geico.hilttabapplication.di

import android.app.Application
import com.geico.hilttabapplication.data.AppDao
import com.geico.hilttabapplication.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDB(context: Application): AppDatabase {
        return AppDatabase.getAppDb(context)
    }
    @Singleton
    @Provides
    fun getDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.getDao()
    }
}