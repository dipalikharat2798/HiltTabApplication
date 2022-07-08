package com.geico.hilttabapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltTabApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}