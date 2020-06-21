package com.anioncode.gamevideodagger.dagger

import com.anioncode.gamevideodagger.main.home.MainFragmentBuildersModule
import com.anioncode.gamevideodagger.network.AuthModule
import com.anioncode.gamevideodagger.viewmodels.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


//Here we are creating a module ,part of Logic out Dagger Aplication
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        VideoModule::class,
        ViewModelFactoryModule::class,
        MainFragmentBuildersModule::class,
        DataViewModelsModule::class,
        AuthModule::class
    ]
)

interface AppComponent :
    AndroidInjector<DaggerApplication> //here we can overide method in our Dagger generated class and we can pass a data .