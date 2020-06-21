package com.anioncode.gamevideodagger.dagger

import com.anioncode.gamevideodagger.main.MainActivity
import com.anioncode.gamevideodagger.main.home.MainFragmentBuildersModule
import com.anioncode.gamevideodagger.network.AuthModule
import com.anioncode.smogu.Adapter.LatestAdapter
import com.anioncode.smogu.Adapter.TopAdapter
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
        open fun provideAdapter(): TopAdapter {
            return TopAdapter();
        }
        @Singleton
        @Provides
        @JvmStatic
        open fun provideAdapter1(): LatestAdapter {
            return LatestAdapter();
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