package com.example.krasikovsurfeducation

import android.app.Application
import com.example.krasikovsurfeducation.di.component.AppComponent
import com.example.krasikovsurfeducation.di.component.DaggerAppComponent
import com.example.krasikovsurfeducation.di.module.NetModule

class BaseApp: Application() {

    val mAppComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .netModule(NetModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        mAppComponent.inject(this)
    }

    fun getAppComponent(): AppComponent = mAppComponent
}