package com.anioncode.gamevideodagger.main.databaseFragment.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.anioncode.gamevideodagger.main.databaseFragment.data.WordDao
import com.anioncode.gamevideodagger.main.databaseFragment.data.WordRoomDatabase
import com.anioncode.gamevideodagger.main.databaseFragment.repository.WordRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object DbModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideTvMazeDatabase(application: Application): WordRoomDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            WordRoomDatabase::class.java, WordRoomDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideShowDao(gameDatabase: WordRoomDatabase): WordDao {
        return gameDatabase.wordDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideWordRepository(wordDao: WordDao): WordRepository {
        return WordRepository(wordDao)
    }

}