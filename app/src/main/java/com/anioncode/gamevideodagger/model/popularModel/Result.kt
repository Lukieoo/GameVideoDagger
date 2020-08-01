package com.anioncode.gamevideodagger.model.popularModel

import java.io.Serializable

data class Result(
    val added: Int=0,
    val added_by_status: AddedByStatus? =null,
    var background_image: String="",
    val clip: Clip? =null,
    val dominant_color: String="",
    var genres: List<Genre>? = null,
    var id: Int =0,
    val metacritic: Int=0,
    var name: String="0",
    val parent_platforms: List<ParentPlatform>? = null,
    val platforms: List<PlatformX>? = null,
    val playtime: Int? = null,
    var rating: Double? = null,
    var rating_top: Int? = null,
    val ratings: List<Rating>? = null,
    val ratings_count: Int? = null,
    var released: String? = null,
    val reviews_count: Int? = null,
    val reviews_text_count: Int? = null,
    val saturated_color: String? = null,
    val score: Any? = null,
    val short_screenshots: List<ShortScreenshot>? = null,
    val slug: String? = null,
    val stores: List<Store>? = null,
    val suggestions_count: Int? = null,
    val tags: List<Tag>? = null,
    val tba: Boolean? = null,
    val user_game: Any? = null
):Serializable