package com.example.krasikovsurfeducation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "mem_table")
data class MemDto (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") @SerializedName("id") var id: Long?,
    @ColumnInfo(name = "title") @SerializedName("title") var title: String,
    @ColumnInfo(name = "description") @SerializedName("description") var description: String,
    @ColumnInfo(name = "isFavorite") @SerializedName("isFavorite") var isFavorite: Boolean,
    @ColumnInfo(name = "createdDate") @SerializedName("createdDate") var createdDate: Long,
    @ColumnInfo(name = "photoUtl") @SerializedName("photoUtl") var photoUtl: String
) : Serializable