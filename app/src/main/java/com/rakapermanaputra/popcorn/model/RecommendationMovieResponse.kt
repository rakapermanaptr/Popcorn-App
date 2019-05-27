package com.rakapermanaputra.popcorn.model

data class RecommendationMovieResponse(
    val page: Int,
    val results: List<RecommendationMovie>
)