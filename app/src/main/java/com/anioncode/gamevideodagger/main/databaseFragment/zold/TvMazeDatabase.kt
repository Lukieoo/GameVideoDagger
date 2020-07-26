package com.anioncode.gamevideodagger.main.databaseFragment.zold

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteShow::class], version = 2, exportSchema = false)
abstract class TvMazeDatabase : RoomDatabase() {

    abstract fun showDao(): ShowDao

    companion object {
        const val DATABASE_NAME = "tvmaze.db"
    }
}