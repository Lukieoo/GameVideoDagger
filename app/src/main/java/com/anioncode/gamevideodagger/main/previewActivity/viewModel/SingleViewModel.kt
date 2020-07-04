package com.anioncode.gamevideodagger.main.previewActivity.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.model.dataState.DataWithStates
import com.anioncode.gamevideodagger.model.detailModel.InfoGame
import com.anioncode.gamevideodagger.network.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SingleViewModel : ViewModel {

    private val authApi: AuthApi

    private val gameInfo: MediatorLiveData<InfoGame> = MediatorLiveData<InfoGame>()

    @Inject constructor( authApis: AuthApi) {

        authApi = authApis
    }


    fun getInfoAboutGame(dates: String) {
        val source: LiveData<DataWithStates<InfoGame>> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getInfo(dates).map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<DataWithStates<InfoGame>>(source
        ) { t ->
            //   Log.d("TAG", "VideoonChanged: $t")
            gameInfo.value=t.data
            gameInfo.removeSource(source)
        }
    }


    fun observeSingle(): LiveData<InfoGame?>? {
        return gameInfo
    }





}