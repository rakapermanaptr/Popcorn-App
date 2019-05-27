package com.rakapermanaputra.popcorn.feature.detail.detail_movie.Info

import com.rakapermanaputra.popcorn.model.DetailMovie
import com.rakapermanaputra.popcorn.model.RecommendationMovie
import com.rakapermanaputra.popcorn.model.SimilarMovie

interface InfoDetailContract {

    interface View {
        fun showDetail(detail: DetailMovie)
        fun showSimilarMovies(similar: List<SimilarMovie>)
        fun showRecommendation(recommend: List<RecommendationMovie>)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun getSimilarMovies(id: Int)
        fun getRecommendationMovies(id: Int)
        fun onDestroy()
    }
}