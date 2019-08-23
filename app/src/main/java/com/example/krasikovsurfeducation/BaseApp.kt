package com.example.krasikovsurfeducation

import android.app.Application
import com.example.krasikovsurfeducation.di.component.AppComponent
import com.example.krasikovsurfeducation.di.component.DaggerAppComponent
import com.example.krasikovsurfeducation.di.module.NetModule

class BaseApp: Application() {
    private lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
            .netModule(NetModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent = mAppComponent
}