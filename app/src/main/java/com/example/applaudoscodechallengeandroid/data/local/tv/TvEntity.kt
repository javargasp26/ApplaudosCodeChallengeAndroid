package com.example.applaudoscodechallengeandroid.data.local.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applaudoscodechallengeandroid.data.network.tv_list.TvInfoResponse
import com.example.applaudoscodechallengeandroid.domain.TvInfo

@Entity(tableName = "tv_list_table")
data class TvEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int =0,
    @ColumnInfo(name = "tvId") val tvId:Int,
    @ColumnInfo(name = "tvName") val tvName: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "rating") val rating: String?,
    @ColumnInfo(name = "overview") val overview: String?
    )

fun TvInfo.toDatabase() = TvEntity(0, tvId, title, image, rating,overview)