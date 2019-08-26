package com.example.krasikovsurfeducation.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.krasikovsurfeducation.BaseApp
import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.database.MemRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val app: Application) {
    @Provides
    @Singleton
    fun provideContext(application: BaseApp): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideMemDatabase(): MemRoomDatabase {
        return Room.databaseBuilder(
            app,
            MemRoomDatabase::class.java,
            MemRoomDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideShowDao(memRoomDatabase: MemRoomDatabase): MemDao {
        return memRoomDatabase.memDao()
    }
}