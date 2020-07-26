package com.anioncode.gamevideodagger.main.databaseFragment.zold

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object TvMazeDbModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideTvMazeDatabase(context: Context): TvMazeDatabase {
        return Room.databaseBuilder(
            context,
            TvMazeDatabase::class.java, TvMazeDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideShowDao(tvMazeDatabase: TvMazeDatabase): ShowDao {
        return tvMazeDatabase.showDao()
    }

}