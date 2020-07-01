package com.anioncode.gamevideodagger.network

import com.anioncode.gamevideodagger.model.detailModel.InfoGame
import com.anioncode.gamevideodagger.model.genresModel.genresModel
import com.anioncode.gamevideodagger.model.plaformModel.platformModel
import com.anioncode.gamevideodagger.model.popularModel.TopGames
import com.anioncode.gamevideodagger.model.searchModel.SearchModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthApi {
//
//    @GET("/users/{id}")
//    fun getUsers(   @Path("id")  id:Int ): Flowable<tmpUsersItem>

    @GET("/api/games")
    fun getTopGames(@Query("dates")  dates:String,@Query("ordering")  ordering:String): Flowable<TopGames>

    @GET("/api/games/{name}")
    fun getInfo(   @Path("name")  name:String ): Flowable<InfoGame>

    @GET("/api/games")
    fun getSearchStatus(  @Query("page_size")  page_size:String,@Query("search")  search:String,@Query("page")  page:Int): Flowable<SearchModel>

    @GET("/api/games")
    fun getSearchStatus(@Query("page_size") page_size:String, @Query("search") search:String, @Query("genres") genres:String, @Query("page") page: Int?): Flowable<SearchModel>


    @GET("/api/games")
    fun getSearchStatuswithPlatform(@Query("page_size") page_size:String, @Query("search") search:String, @Query("genres") genres: String? = null, @Query("page") page: Int?, @Query("platforms") parent_platforms: String? = null): Flowable<SearchModel>

    @GET("/api/games")
    fun getSearchStatuswithPlatform2(@Query("page_size") page_size:String, @Query("search") search:String, @Query("page") page: Int?, @Query("platforms") parent_platforms: String? = null): Flowable<SearchModel>

    @GET("/api/genres")
    fun getGenres( ): Flowable<genresModel>

    @GET("/api/platforms")
    fun getPlatform( ): Flowable<platformModel>
}
