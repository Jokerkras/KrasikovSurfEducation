package com.example.krasikovsurfeducation.di.component

import com.example.krasikovsurfeducation.di.module.NetModule
import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.repo.UserStorage
import com.example.krasikovsurfeducation.view.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetModule::class))
interface AppComponent {
    fun inject(loginRepository: LoginRepository)
    fun inject(loginActivity: LoginActivity)
}