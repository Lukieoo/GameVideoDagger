package com.anioncode.gamevideodagger.main.mainActivity.viewModel

import androidx.lifecycle.*
import com.anioncode.gamevideodagger.model.dataState.DataWithStates
import com.anioncode.gamevideodagger.model.popularModel.TopGames
import com.anioncode.gamevideodagger.network.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class VideoViewModel : ViewModel {

    private val authApi: AuthApi

    private val games: MediatorLiveData<TopGames> = MediatorLiveData<TopGames>()
    private val latestGames: MediatorLiveData<TopGames> = MediatorLiveData<TopGames>()

    @Inject
    constructor(authApis: AuthApi) {

        authApi = authApis
    }


    fun authenticateWithId(dates: String) {
        val source: LiveData<DataWithStates<TopGames>> = LiveDataReactiveStreams.fromPublisher(
            authApi.getTopGames(dates, "-added").map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )

        games.addSource<DataWithStates<TopGames>>(source
        ) { t ->
            //   Log.d("TAG", "VideoonChanged: $t")
            games.value = t.data
            games.removeSource(source)
        }
    }



    fun observeGaneInfo(): LiveData<TopGames?>? {
        return games
    }

    fun authenticateWithString(dates: String) {

        val source:  LiveData<DataWithStates<TopGames>> = LiveDataReactiveStreams.fromPublisher(
            authApi.getTopGames(dates, "-added").map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )

        latestGames.addSource<DataWithStates<TopGames>>(source
        ) { t ->
            //   Log.d("TAG", "VideoonChanged: $t")
            latestGames.value = t.data
            latestGames.removeSource(source)
        }
    }

    fun observeLatestGames(): LiveData<TopGames?>? {
        return latestGames
    }


}