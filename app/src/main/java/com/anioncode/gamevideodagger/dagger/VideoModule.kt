package com.anioncode.gamevideodagger.dagger

import com.anioncode.gamevideodagger.MainActivity
import com.anioncode.gamevideodagger.network.AuthModule
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

    @ContributesAndroidInjector(
        modules = [
            DataViewModelsModule::class,
                    AuthModule::class
        ]
    )

    abstract fun contributeMainActivity(): MainActivity //Must call how our main Activity

    companion object {
        @Provides
        @JvmStatic
        open fun provideAdapter(): TopAdapter {
            return TopAdapter();
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