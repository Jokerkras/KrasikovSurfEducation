package com.example.krasikovsurfeducation.di.module

import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.repo.MemRepository
import com.example.krasikovsurfeducation.mvp.presenters.LoginPresenter
import com.example.krasikovsurfeducation.mvp.presenters.MemListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MVPModule {

    @Provides
    @Singleton
    fun provideLoginPresenter(loginRepo: LoginRepository): LoginPresenter = LoginPresenter(loginRepo)

    @Provides
    @Singleton
    fun provideMemListPresenter(memRepository: MemRepository): MemListPresenter = MemListPresenter(memRepository)
}