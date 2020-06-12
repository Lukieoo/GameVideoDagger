package com.anioncode.gamevideodagger.Dagger

import com.anioncode.gamevideodagger.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


//Annotation must have
@Module
abstract class VideoModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity //Must call how our main Activity


}