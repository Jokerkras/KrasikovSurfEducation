package com.example.krasikovsurfeducation.dao

import androidx.room.*
import com.example.krasikovsurfeducation.model.MemDto
import io.reactivex.Flowable

@Dao
interface MemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMems(mems: List<MemDto>)

    @Update
    fun updateUsers(mems: List<MemDto>)

    @Delete
    fun deleteUsers(mems: List<MemDto>)

    @Query("SELECT * FROM mem_table")
    fun getMems(): Flowable<List<MemDto>>
}