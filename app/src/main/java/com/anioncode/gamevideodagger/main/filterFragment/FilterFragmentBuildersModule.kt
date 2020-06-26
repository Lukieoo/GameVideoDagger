package com.anioncode.gamevideodagger.main.filterFragment

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.anioncode.gamevideodagger.main.previewActivity.PreviewGameActivity
import com.anioncode.smogu.Adapter.FilterAdapter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class FilterFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFilterFragment(): FilterFragment //Must call how our main Fragment

    companion object {
        @Singleton
        @Provides
        @JvmStatic
        open fun provideAdapterFilter(application: Application): FilterAdapter {
            return FilterAdapter(itemClick = object : FilterAdapter.OnClickAdapterListner {

                  override fun onClick(game: com.anioncode.gamevideodagger.model.searchModel.Result) {
                    // Toast.makeText(application.applicationContext,"Dziala",Toast.LENGTH_LONG).show()
                    var intent = Intent(
                        application.applicationContext,
                        PreviewGameActivity::class.java
                    ).apply {
                        putExtra("GameListBackround", game.background_image)
                        putExtra("GameListColor", game.dominant_color)

                        putExtra("GameData", Gson().toJson(game))

                    }
                    intent.flags = FLAG_ACTIVITY_NEW_TASK
                    application.applicationContext.startActivity(intent)
                }

            });
        }
    }
}