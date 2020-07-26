package com.anioncode.gamevideodagger.main.databaseFragment.zold

import androidx.room.*
import io.reactivex.Single


@Dao
interface ShowDao {

    @Query("SELECT * FROM favourite_shows")
    fun getAllFavouriteShows(): Single<List<FavoriteShow?>?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteShow: FavoriteShow)

    @Delete
    fun remove(favoriteShow: FavoriteShow)

    @Query("SELECT id from favourite_shows")
    fun getFavoriteShowIds(): List<Long>

    @Query("DELETE FROM favourite_shows")
    fun clear()

    @Query("SELECT count(*) FROM favourite_shows where id LIKE :id")
    fun isFavouriteShow(id: Long): Int

}