package com.rakapermanaputra.popcorn.feature.movies.top_rated

import com.rakapermanaputra.popcorn.model.Movies

interface MoviesTopRatedContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showTopRated(movies: List<Movies>)
    }

    interface Presenter {
        fun getTopRated()
        fun onDestroy()
    }
}