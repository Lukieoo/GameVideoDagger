package com.anioncode.gamevideodagger.network

import com.anioncode.gamevideodagger.model.ranked.Result
import com.anioncode.gamevideodagger.model.ranked.TopGames
import com.anioncode.gamevideodagger.model.tmpmodel.tmpUsersItem
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthApi {

    @GET("/users/{id}")
    fun getUsers(   @Path("id")  id:Int ): Flowable<tmpUsersItem>

    @GET("/api/games")
    fun getTopGames(@Query("dates")  dates:String,@Query("ordering")  ordering:String): Flowable<TopGames>
}
