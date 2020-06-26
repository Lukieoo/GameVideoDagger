package com.anioncode.gamevideodagger.model.popularModel

data class TopGames(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>,
    val user_platforms: Boolean
)