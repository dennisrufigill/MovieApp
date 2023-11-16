package com.example.movieapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    private lateinit var instance: MyApplication


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getInstance(): MyApplication? {
        return instance
    }
}