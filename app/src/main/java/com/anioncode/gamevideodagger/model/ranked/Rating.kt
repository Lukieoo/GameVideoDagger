package com.anioncode.gamevideodagger.model.ranked

data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)