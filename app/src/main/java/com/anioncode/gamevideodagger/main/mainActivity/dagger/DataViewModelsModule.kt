package com.anioncode.gamevideodagger.main.mainActivity.dagger

import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.viewmodels.VideoViewModel
import com.anioncode.gamevideodagger.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DataViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(VideoViewModel::class)
    abstract fun bindVideoViewModel(viewModel: VideoViewModel): ViewModel

}