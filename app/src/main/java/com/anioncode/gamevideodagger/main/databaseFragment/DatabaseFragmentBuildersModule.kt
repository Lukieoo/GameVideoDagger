package com.anioncode.gamevideodagger.main.databaseFragment

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.anioncode.gamevideodagger.main.databaseFragment.entity.Game
//import com.anioncode.gamevideodagger.main.databaseFragment.di.RoomModule
import com.anioncode.gamevideodagger.main.previewActivity.PreviewGameActivity
import com.anioncode.gamevideodagger.model.genresModel.Result
import com.anioncode.smogu.Adapter.FilterAdapter
import com.anioncode.smogu.Adapter.GangerAdapter
import com.anioncode.smogu.Adapter.PlatformAdapter
import com.anioncode.smogu.Adapter.RoomDbAdapter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
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
        open fun provideAdapter(application: Application): RoomDbAdapter {
            return RoomDbAdapter(itemClick = object : RoomDbAdapter.OnClickAdapterListner {

//                override fun onClick(game: Result) {
//                    // Toast.makeText(application.applicationContext,"Dziala",Toast.LENGTH_LONG).show()
////                    var intent = Intent(
////                        application.applicationContext,
////                        PreviewGameActivity::class.java
////                    ).apply {
////                        putExtra("GameListBackround", game.background_image)
////                        putExtra("GameListColor", game.dominant_color)
////
////                        putExtra("GameData", Gson().toJson(game))
////
////                    }
////                    intent.flags = FLAG_ACTIVITY_NEW_TASK
////                    application.applicationContext.startActivity(intent)
//                }

                override fun onClick(game: Game) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            });
        }
    }


}