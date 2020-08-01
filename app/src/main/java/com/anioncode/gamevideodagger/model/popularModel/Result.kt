package com.anioncode.gamevideodagger.model.popularModel

import java.io.Serializable

data class Result(
    val added: Int,
    val added_by_status: AddedByStatus,
    var background_image: String,
    val clip: Clip,
    val dominant_color: String,
    val genres: List<Genre>,
    var id: Int,
    val metacritic: Int,
    var name: String,
    val parent_platforms: List<ParentPlatform>,
    val platforms: List<PlatformX>,
    val playtime: Int,
    val rating: Double,
    val rating_top: Int,
    val ratings: List<Rating>,
    val ratings_count: Int,
    val released: String,
    val reviews_count: Int,
    val reviews_text_count: Int,
    val saturated_color: String,
    val score: Any,
    val short_screenshots: List<ShortScreenshot>,
    val slug: String,
    val stores: List<Store>,
    val suggestions_count: Int,
    val tags: List<Tag>,
    val tba: Boolean,
    val user_game: Any
):Serializable