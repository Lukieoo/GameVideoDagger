package com.anioncode.gamevideodagger.main.mainActivity.viewModel

import android.util.Log.i
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.model.popularModel.TopGames
import com.anioncode.gamevideodagger.network.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.math.log

class VideoViewModel : ViewModel {

    private val authApi: AuthApi

    private val games: MediatorLiveData<TopGames> = MediatorLiveData<TopGames>()
    private val latestGames: MediatorLiveData<TopGames> = MediatorLiveData<TopGames>()

    @Inject
    constructor(authApis: AuthApi) {

        authApi = authApis
    }


    fun authenticateWithId(dates: String) {
        val source: LiveData<TopGames> = LiveDataReactiveStreams.fromPublisher(
            authApi.getTopGames(dates, "-added")
                .subscribeOn(Schedulers.io())
        )
        games.addSource<TopGames>(source, object : androidx.lifecycle.Observer<TopGames?> {

            override fun onChanged(t: TopGames?) {
                //   Log.d("TAG", "VideoonChanged: $t")
                games.value = t
                games.removeSource(source)
            }
        })
    }



    fun observeGaneInfo(): LiveData<TopGames?>? {
        return games
    }

    fun authenticateWithString(dates: String) {
        val source: LiveData<TopGames> = LiveDataReactiveStreams.fromPublisher(
            authApi.getTopGames(dates, "-added")
                .subscribeOn(Schedulers.io())
        )
        latestGames.addSource<TopGames>(source, object : androidx.lifecycle.Observer<TopGames?> {

            override fun onChanged(t: TopGames?) {
                //Log.d("TAG", "VideoonChanged: $t")
                latestGames.setValue(t)
                latestGames.removeSource(source)
            }
        })
    }

    fun observeLatestGames(): LiveData<TopGames?>? {
        return latestGames
    }


}