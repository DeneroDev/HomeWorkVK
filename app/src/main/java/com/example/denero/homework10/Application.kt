package com.example.denero.homework10


import android.os.Bundle
import android.util.Log
import com.vk.sdk.VKSdk

/**
 * Created by Denero on 17.02.2018.
 */
class Application: android.app.Application() {
        override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        Log.d("SDK","ACTIVATED")
    }
}