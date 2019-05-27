package com.rakapermanaputra.popcorn.model

data class SimilarMovieResponse(
    val page: Int,
    val results: List<SimilarMovie>
)