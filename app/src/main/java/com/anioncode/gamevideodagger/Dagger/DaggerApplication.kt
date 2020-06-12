package com.anioncode.gamevideodagger.Dagger

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DaggerApplication : Application(),HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any> //In Kotlin Any but in Java Object

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.create().inject(this)


    }

    override fun androidInjector()= androidInjector

}