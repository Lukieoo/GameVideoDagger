package com.anioncode.gamevideodagger.model.ranked

data class TopGames(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>,
    val user_platforms: Boolean
)