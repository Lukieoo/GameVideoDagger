package com.anioncode.gamevideodagger.main.mainActivity

import android.app.Application
import com.anioncode.gamevideodagger.main.mainActivity.dagger.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

// class create base dagger application
class DaggerApplication : Application(),HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any> //In Kotlin Any but in Java Object

    override fun onCreate() {
        super.onCreate()
//        DaggerAppComponent.create().inject(this)
        DaggerAppComponent.builder().application(this)!!.build().inject(this)

    }

    override fun androidInjector()= androidInjector

}