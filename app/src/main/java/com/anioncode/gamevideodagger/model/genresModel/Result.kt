package com.anioncode.gamevideodagger.model.genresModel

data class Result(
    val following: Boolean,
    val games: List<Game>,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String,
    var clicked: Boolean=false
)