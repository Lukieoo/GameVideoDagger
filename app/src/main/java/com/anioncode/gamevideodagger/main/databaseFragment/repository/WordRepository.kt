/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anioncode.gamevideodagger.main.databaseFragment.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.anioncode.gamevideodagger.main.databaseFragment.data.GameDao
import com.anioncode.gamevideodagger.main.databaseFragment.entity.Game
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */


@Singleton
class WordRepository@Inject
constructor(private val gameDao: GameDao)  {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Game>> = gameDao.getMyGame()
    val allBuy: LiveData<List<Game>> = gameDao.getBuy()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(game: Game) {
        gameDao.insert(game)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        gameDao.deleteAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteID(id:String) {
        gameDao.deleteID(id)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateID(id:String,update:String) {
        gameDao.updateID(id,update)
    }

    fun findID( id:String) : LiveData<List<Game>> {
        return  gameDao.findID(id)
    }
}
