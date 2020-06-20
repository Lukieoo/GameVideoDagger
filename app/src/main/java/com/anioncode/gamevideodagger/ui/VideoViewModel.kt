package com.anioncode.gamevideodagger.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.model.tmpUsersItem
import com.anioncode.gamevideodagger.network.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VideoViewModel : ViewModel {

    private val authApi: AuthApi

    private val authUser: MediatorLiveData<tmpUsersItem> = MediatorLiveData<tmpUsersItem>()

    @Inject constructor( authApis: AuthApi) {

        authApi = authApis
    }

    fun authenticateWithId(userId: Int) {
        val source: LiveData<tmpUsersItem> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getUsers(userId)
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource<tmpUsersItem>(source, object : androidx.lifecycle.Observer<tmpUsersItem?> {

            override fun onChanged(t: tmpUsersItem?) {
                authUser.setValue(t)
                authUser.removeSource(source)
            }
        })
    }

    fun observeUser(): LiveData<tmpUsersItem?>? {
        return authUser
    }



}