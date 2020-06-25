package com.anioncode.gamevideodagger.main.mainActivity.dagger

import android.app.Application
import com.anioncode.gamevideodagger.main.filterFragment.FilterFragmentBuildersModule
import com.anioncode.gamevideodagger.main.homeFragment.MainFragmentBuildersModule
import com.anioncode.gamevideodagger.main.mainActivity.DaggerApplication
import com.anioncode.gamevideodagger.main.previewActivity.PreviewGameActivityBuildersModule
import com.anioncode.gamevideodagger.network.AuthModule
import com.anioncode.gamevideodagger.viewmodels.ViewModelFactoryModule
import dagger.BindsInstance
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
        PreviewGameActivityBuildersModule::class,
        FilterFragmentBuildersModule::class,

        DataViewModelsModule::class,
        AuthModule::class
    ]
)

interface AppComponent :
    AndroidInjector<DaggerApplication> //here we can overide method in our Dagger generated class and we can pass a data .
{
    fun inject(application :Application)
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder
        fun build(): AppComponent
    }
}