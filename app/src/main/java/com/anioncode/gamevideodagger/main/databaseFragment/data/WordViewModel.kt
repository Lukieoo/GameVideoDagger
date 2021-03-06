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

package com.anioncode.gamevideodagger.main.databaseFragment.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anioncode.gamevideodagger.main.databaseFragment.entity.Game
import com.anioncode.gamevideodagger.main.databaseFragment.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class WordViewModel @Inject constructor( var repository:WordRepository ) : ViewModel()   {

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
      var allWords: LiveData<List<Game>> = repository.allWords
      var allBuy: LiveData<List<Game>> = repository.allBuy

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(game: Game) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(game)

    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
      fun findID(id:String): LiveData<List<Game>> {
          return repository.findID(id)
    }
}
