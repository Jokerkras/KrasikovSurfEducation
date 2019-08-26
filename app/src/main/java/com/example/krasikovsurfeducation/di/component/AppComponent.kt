package com.example.krasikovsurfeducation.di.component

import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.di.module.MVPModule
import com.example.krasikovsurfeducation.di.module.NetModule
import com.example.krasikovsurfeducation.mvp.activities.LoginActivity
import com.example.krasikovsurfeducation.mvp.activities.MemListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetModule::class, MVPModule::class))
interface AppComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(memListFragment: MemListFragment)
    fun inject(baseApp: BaseApp)
}