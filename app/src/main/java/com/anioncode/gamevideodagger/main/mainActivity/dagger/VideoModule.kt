package com.anioncode.gamevideodagger.main.mainActivity.dagger

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.anioncode.gamevideodagger.main.mainActivity.MainActivity
import com.anioncode.gamevideodagger.main.previewActivity.PreviewGameActivity
import com.anioncode.gamevideodagger.model.popularModel.Result
import com.anioncode.smogu.Adapter.LatestAdapter
import com.anioncode.smogu.Adapter.TopAdapter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//Annotation must have
@Module
abstract class VideoModule {



    //to powinienem dać do innej klasy...
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity //Must call how our main Activity
    // ...do tego momentu  ponieważ odnosi się to do aktywności , MainActivityBuildersModule coś takiego
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        open fun provideAdapter(application: Application): TopAdapter {
            return TopAdapter (itemClick = object : TopAdapter.OnClickAdapterListner{

                override fun onClick(game: Result) {
                   // Toast.makeText(application.applicationContext,"Dziala",Toast.LENGTH_LONG).show()
                    var intent= Intent(application.applicationContext,
                        PreviewGameActivity::class.java).apply {
                        putExtra("GameListBackround",game.background_image)
                        putExtra("GameListColor",game.dominant_color)

                        putExtra("GameData", Gson().toJson(game))

                    }
                    intent.flags=FLAG_ACTIVITY_NEW_TASK
                    application.applicationContext.startActivity(intent)
                }

            });
        }
        @Singleton
        @Provides
        @JvmStatic
        open fun provideAdapter1(application: Application): LatestAdapter {
            return LatestAdapter(itemClick = object : LatestAdapter.OnClickAdapterListner {

                override fun onClick(game: Result) {
                    // Toast.makeText(application.applicationContext,"Dziala",Toast.LENGTH_LONG).show()
                    var intent= Intent(application.applicationContext,
                        PreviewGameActivity::class.java).apply {
                        putExtra("GameListBackround",game.background_image)
                        putExtra("GameListColor",game.dominant_color)

                        putExtra("GameData", Gson().toJson(game))

                    }
                    intent.flags=FLAG_ACTIVITY_NEW_TASK
                    application.applicationContext.startActivity(intent)
                }

            });
        }

        @Singleton
        @Provides
        @JvmStatic
        open fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com")
                .baseUrl("https://api.rawg.io")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}