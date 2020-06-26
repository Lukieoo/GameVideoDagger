package com.anioncode.gamevideodagger.model.searchModel

data class SearchModel(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>,
    val user_platforms: Boolean
)