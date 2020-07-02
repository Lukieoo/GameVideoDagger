package com.anioncode.gamevideodagger.main.ConnectionFragment

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.anioncode.gamevideodagger.main.previewActivity.PreviewGameActivity
import com.anioncode.gamevideodagger.model.genresModel.Result
import com.anioncode.smogu.Adapter.FilterAdapter
import com.anioncode.smogu.Adapter.GangerAdapter
import com.anioncode.smogu.Adapter.PlatformAdapter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import java.util.*
import javax.inject.Singleton
import kotlin.Comparator
import kotlin.collections.ArrayList


@Module
abstract class ConnectionFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeConnectionFragment(): ConnectionFragment //Must call how our main Fragment

}