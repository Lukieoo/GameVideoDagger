package com.anioncode.gamevideodagger.main.mainActivity.viewModel

import android.util.Log.i
import androidx.lifecycle.*
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
            authApi.getTopGames(dates, "-added").doOnError { t-> print("$t  doOnError") }
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
        var onError:Boolean=false
        val source: LiveData<TopGames> = LiveDataReactiveStreams.fromPublisher(
            authApi.getTopGames(dates, "-added").doOnError { t: Throwable ->  onError=true }
                .subscribeOn(Schedulers.io())
        )
        if (!onError) {
            latestGames.addSource<TopGames>(
                source,
                object : androidx.lifecycle.Observer<TopGames?> {

                    override fun onChanged(t: TopGames?) {
                        //Log.d("TAG", "VideoonChanged: $t")
                        latestGames.setValue(t)
                        latestGames.removeSource(source)
                    }
                })
        }
    }

    fun observeLatestGames(): LiveData<TopGames?>? {
        return latestGames
    }


}