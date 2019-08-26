package com.example.krasikovsurfeducation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "mem_table")
data class MemDto (
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") val id: Long,
    @ColumnInfo(name = "title") @SerializedName("title") val title: String,
    @ColumnInfo(name = "description") @SerializedName("description") val description: String,
    @ColumnInfo(name = "isFavorite") @SerializedName("isFavorite") val isFavorite: Boolean,
    @ColumnInfo(name = "createdDate") @SerializedName("createdDate") val createdDate: Long,
    @ColumnInfo(name = "photoUtl") @SerializedName("photoUtl") val photoUtl: String
) : Serializable