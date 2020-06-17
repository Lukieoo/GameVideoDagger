package com.anioncode.gamevideodagger.dagger

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthApi {

    @get:GET("fake")
    val fakeStuff: Call<ResponseBody?>?

}
