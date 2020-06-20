package com.anioncode.gamevideodagger.network

import com.anioncode.gamevideodagger.model.tmpUsers
import com.anioncode.gamevideodagger.model.tmpUsersItem
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("/users/{id}")
    fun getUsers(   @Path("id")  id:Int ): Flowable<tmpUsersItem>

}
