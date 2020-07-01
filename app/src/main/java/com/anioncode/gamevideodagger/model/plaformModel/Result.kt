package com.anioncode.gamevideodagger.model.plaformModel

data class Result(
    val games: List<Game>,
    val games_count: Int,
    val id: Int,
    val image: Any,
    val image_background: String,
    val name: String,
    val slug: String,
    val year_end: Any,
    val year_start: Any,
    var clicked: Boolean
)