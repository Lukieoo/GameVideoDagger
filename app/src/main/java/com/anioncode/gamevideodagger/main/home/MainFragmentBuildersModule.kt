package com.anioncode.gamevideodagger.main.home

import com.anioncode.gamevideodagger.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

        @ContributesAndroidInjector
        abstract fun contributeHomeFragment(): HomeFragment //Must call how our main Activity
}