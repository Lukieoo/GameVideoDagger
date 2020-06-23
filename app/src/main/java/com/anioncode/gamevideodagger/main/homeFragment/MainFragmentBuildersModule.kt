package com.anioncode.gamevideodagger.main.homeFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

        @ContributesAndroidInjector
        abstract fun contributeHomeFragment(): HomeFragment //Must call how our main Activity
}