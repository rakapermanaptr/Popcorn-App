package com.rakapermanaputra.popcorn.feature.detail.detail_people.movies

import com.rakapermanaputra.popcorn.model.Credits

interface MovieCreditsContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMovieCredits(movies: List<Credits>)
    }

    interface Presenter {
        fun getMovieCredits(id: Int)
        fun onDestroy()
    }
}