package com.anioncode.gamevideodagger.dagger

import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.ui.VideoViewModel
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