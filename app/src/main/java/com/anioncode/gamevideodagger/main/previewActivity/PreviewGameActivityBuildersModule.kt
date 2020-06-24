package com.anioncode.gamevideodagger.main.previewActivity

import com.anioncode.smogu.Adapter.LatestAdapter
import com.anioncode.smogu.Adapter.ScreenAdapter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class PreviewGameActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePreviewGameActivity(): PreviewGameActivity //Must call how our main Activity

    companion object {
        @Singleton
        @Provides
        @JvmStatic
        open fun provideAdapterScreen(): ScreenAdapter {
            return ScreenAdapter();
        }
    }
}