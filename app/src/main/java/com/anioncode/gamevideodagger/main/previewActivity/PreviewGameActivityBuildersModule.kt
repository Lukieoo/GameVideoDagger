package com.anioncode.gamevideodagger.main.previewActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PreviewGameActivityBuildersModule {

        @ContributesAndroidInjector
        abstract fun contributePreviewGameActivity(): PreviewGameActivity //Must call how our main Activity
}