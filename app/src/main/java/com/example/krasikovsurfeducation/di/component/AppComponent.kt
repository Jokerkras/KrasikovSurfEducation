package com.example.krasikovsurfeducation.di.component

import com.example.krasikovsurfeducation.di.module.MVPModule
import com.example.krasikovsurfeducation.di.module.NetModule
import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.view.LoginActivity
import com.example.krasikovsurfeducation.view.MemListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetModule::class, MVPModule::class))
interface AppComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(memListFragment: MemListFragment)
}