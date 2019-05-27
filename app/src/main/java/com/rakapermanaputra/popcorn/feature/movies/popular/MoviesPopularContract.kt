package com.rakapermanaputra.popcorn.feature.movies.popular

import com.rakapermanaputra.popcorn.model.Movies

interface MoviesPopularContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showPopular(movies: List<Movies>)
    }

    interface Presenter {
        fun getPopular()
        fun onDestroy()
    }
}