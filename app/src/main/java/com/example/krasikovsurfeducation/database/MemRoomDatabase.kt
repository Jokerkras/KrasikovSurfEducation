package com.example.krasikovsurfeducation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.krasikovsurfeducation.dao.MemDao
import com.example.krasikovsurfeducation.model.MemDto

@Database(entities = [MemDto::class], version = 3, exportSchema = false)
abstract class MemRoomDatabase: RoomDatabase() {

    abstract fun memDao(): MemDao

    companion object {
        val DATABASE_NAME = "Mem_database"
    }
}