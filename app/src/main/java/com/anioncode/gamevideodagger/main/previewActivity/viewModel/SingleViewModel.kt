package com.anioncode.gamevideodagger.main.previewActivity.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.model.gamemodel.InfoGame
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
        val source: LiveData<InfoGame> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getInfo(dates)
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<InfoGame>(source, object : androidx.lifecycle.Observer<InfoGame?> {

            override fun onChanged(t: InfoGame?) {
             //   Log.d("TAG", "VideoonChanged: $t")
                gameInfo.setValue(t)
                gameInfo.removeSource(source)
            }
        })
    }


    fun observeSingle(): LiveData<InfoGame?>? {
        return gameInfo
    }





}