package com.anioncode.gamevideodagger.main.databaseFragment.zold
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_shows")
data class FavoriteShow(
    @PrimaryKey
    var id: Long,
    var name: String,
    var premiered: String?,
    var imageUrl: String?,
    var summary: String?,
    var rating: String?,
    var runtime: Int?,
    var isFavorite: Boolean
)