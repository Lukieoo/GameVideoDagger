package com.anioncode.gamevideodagger.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.dagger.AuthApi
import javax.inject.Inject

class VideoViewModel : ViewModel {

    private val authApi: AuthApi
    @Inject constructor( authApis:AuthApi ){

        authApi = authApis
        Log.d(
            "ViewModel_Anioncode",
            "VideoViewModel: viewmodel is working..."
        )
    }

}