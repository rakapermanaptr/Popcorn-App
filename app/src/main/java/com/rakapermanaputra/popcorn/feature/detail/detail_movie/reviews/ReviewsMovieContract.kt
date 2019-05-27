package com.rakapermanaputra.popcorn.feature.detail.detail_movie.reviews

import com.rakapermanaputra.popcorn.model.Review

interface ReviewsMovieContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showReviews(reviews: List<Review>)
    }

    interface Presenter {
        fun getReviews(id: Int)
        fun onDestroy()
    }
}