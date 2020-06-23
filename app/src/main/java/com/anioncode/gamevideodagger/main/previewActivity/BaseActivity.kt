package com.anioncode.gamevideodagger.main.previewActivity

import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity(),HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any> //In Kotlin Any but in Java Object


    override fun androidInjector()= androidInjector

}