package com.anioncode.gamevideodagger.main.filterFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FilterFragmentBuildersModule {

        @ContributesAndroidInjector
        abstract fun contributeFilterFragment(): FilterFragment //Must call how our main Activity

}