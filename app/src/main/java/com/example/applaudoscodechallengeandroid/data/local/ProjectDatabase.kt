package com.example.applaudoscodechallengeandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.applaudoscodechallengeandroid.data.local.tv.TvDao
import com.example.applaudoscodechallengeandroid.data.local.tv.TvEntity

@Database(entities = [TvEntity::class], version = 3)
abstract class ProjectDatabase: RoomDatabase() {

    abstract fun getTvDao(): TvDao

}