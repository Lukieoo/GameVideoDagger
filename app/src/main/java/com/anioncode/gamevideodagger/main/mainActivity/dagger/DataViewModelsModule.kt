package com.anioncode.gamevideodagger.main.mainActivity.dagger

import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.main.filterFragment.viewModel.FilterViewModel
import com.anioncode.gamevideodagger.main.previewActivity.viewModel.SingleViewModel
import com.anioncode.gamevideodagger.main.mainActivity.viewModel.VideoViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(SingleViewModel::class)
    abstract fun bindSingleViewModel(viewModel: SingleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    abstract fun bindFilterViewModel(viewModel: FilterViewModel): ViewModel
}