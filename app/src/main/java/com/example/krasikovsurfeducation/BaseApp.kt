package com.example.krasikovsurfeducation

import android.app.Application
import com.example.krasikovsurfeducation.di.component.AppComponent
import com.example.krasikovsurfeducation.di.component.DaggerAppComponent
import com.example.krasikovsurfeducation.di.module.DatabaseModule
import com.example.krasikovsurfeducation.di.module.NetModule

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
            .netModule(NetModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
        mAppComponent.inject(this)
    }

    companion object {
        lateinit var mAppComponent: AppComponent

        fun getAppComponent(): AppComponent = mAppComponent
    }
}