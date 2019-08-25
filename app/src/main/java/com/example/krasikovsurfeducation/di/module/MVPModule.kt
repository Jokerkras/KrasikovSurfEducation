package com.example.krasikovsurfeducation.di.module

import com.example.krasikovsurfeducation.repo.LoginRepository
import com.example.krasikovsurfeducation.repo.MemRepository
import com.example.krasikovsurfeducation.view.presenter.LoginPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MVPModule {

    @Provides
    @Singleton
    fun provideLoginPresenter(loginRepo: LoginRepository, memRepo: MemRepository): LoginPresenter = LoginPresenter(loginRepo, memRepo)
}