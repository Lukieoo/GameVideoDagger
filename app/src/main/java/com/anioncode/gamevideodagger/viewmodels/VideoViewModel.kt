package com.anioncode.gamevideodagger.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.model.ranked.Result
import com.anioncode.gamevideodagger.model.ranked.TopGames
import com.anioncode.gamevideodagger.network.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VideoViewModel : ViewModel {

    private val authApi: AuthApi

    private val authUser: MediatorLiveData<TopGames> = MediatorLiveData<TopGames>()
    private val latestGames: MediatorLiveData<TopGames> = MediatorLiveData<TopGames>()

    @Inject constructor( authApis: AuthApi) {

        authApi = authApis
    }


    fun authenticateWithId(dates: String) {
        val source: LiveData<TopGames> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getTopGames(dates,"-added")
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource<TopGames>(source, object : androidx.lifecycle.Observer<TopGames?> {

            override fun onChanged(t: TopGames?) {
             //   Log.d("TAG", "VideoonChanged: $t")
                authUser.setValue(t)
                authUser.removeSource(source)
            }
        })
    }


    fun observeUser(): LiveData<TopGames?>? {
        return authUser
    }

    fun authenticateWithString(dates: String) {
        val source: LiveData<TopGames> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getTopGames(dates,"-added")
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