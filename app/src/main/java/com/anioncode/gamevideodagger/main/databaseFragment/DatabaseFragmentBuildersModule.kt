package com.anioncode.gamevideodagger.main.databaseFragment

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.anioncode.gamevideodagger.main.databaseFragment.entity.Game
import com.anioncode.gamevideodagger.main.databaseFragment.repository.WordRepository
//import com.anioncode.gamevideodagger.main.databaseFragment.di.RoomModule
import com.anioncode.gamevideodagger.main.previewActivity.PreviewGameActivity
import com.anioncode.gamevideodagger.model.genresModel.Result
import com.anioncode.smogu.Adapter.*
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Singleton
import kotlin.Comparator
import kotlin.collections.ArrayList


@Module
abstract class DatabaseFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDatabaseFragment(): DatabaseFragment //Must call how our main Fragment{}
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        open fun provideAdapter(application: Application,repository: WordRepository): RoomDbAdapter {
            return RoomDbAdapter(itemClick = object : RoomDbAdapter.OnClickAdapterListner {

                override fun onClick(game: Game) {
                    GlobalScope.launch {
                        repository.deleteID(game.id)
                    }
                }

            });
        }
        @Singleton
        @Provides
        @JvmStatic
        open fun provideRoomBuyAdapter(application: Application,repository: WordRepository): RoomBuyAdapter {
            return RoomBuyAdapter(itemClick = object : RoomBuyAdapter.OnClickAdapterListner {

                override fun onClick(game: Game) {

                    GlobalScope.launch {
                        repository.deleteID(game.id)
                    }
                }

                override fun onStore(game: Game) {

                    GlobalScope.launch {
                        repository.updateID(game.id,"store")
                    }
                }

            });
        }
    }


}