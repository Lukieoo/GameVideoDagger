package com.anioncode.gamevideodagger.model.genresModel

data class genresModel(
    val count: Int,
    val description: String,
    val next: Any,
    val previous: Any,
    val results: List<Result>,
    val seo_description: String,
    val seo_h1: String,
    val seo_title: String
)