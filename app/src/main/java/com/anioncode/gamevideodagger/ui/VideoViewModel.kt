package com.anioncode.gamevideodagger.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.model.tmpUsers
import com.anioncode.gamevideodagger.model.tmpUsersItem
import com.anioncode.gamevideodagger.network.AuthApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VideoViewModel : ViewModel {

    private val authApi: AuthApi
    @Inject constructor( authApis: AuthApi){

        authApi = authApis

        if(authApi==null){

            Log.d(
                "ViewModel_Anioncode",
                "VideoViewModel: viewmodel is not working..."
            )
        }else{
            authApi.getUsers(1).toObservable().subscribeOn(Schedulers.io())
                .subscribe( object : Observer<tmpUsersItem>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: tmpUsersItem) {
                        Log.d(
                            "ViewModel_Anioncode",
                            "tmpUsersItem:$t"
                        )
                    }

                    override fun onError(e: Throwable) {
                        Log.d(
                            "ViewModel_Anioncode",
                            "VideoViewModel:$e"
                        )
                    }
                })
            Log.d(
                "ViewModel_Anioncode",
                "VideoViewModel: viewmodel is working..."
            )
        }
    }

}