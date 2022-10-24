package com.example.applaudoscodechallengeandroid.data.local.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TvDao {

    @Query("SELECT * FROM tv_list_table ORDER BY tvId ASC")
    suspend fun getAllTvs(): List<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTv(tvList: List<TvEntity>)

    @Query("DELETE FROM tv_list_table")
    suspend fun clearTvTable()
}
