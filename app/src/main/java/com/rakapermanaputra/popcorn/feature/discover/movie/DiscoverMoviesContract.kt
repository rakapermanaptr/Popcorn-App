package com.rakapermanaputra.popcorn.feature.discover.movie

import com.rakapermanaputra.popcorn.model.Movies

interface DiscoverMoviesContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDiscoverMovies(discover: List<Movies>)
    }

    interface Presenter {
        fun getDiscoverMovie()
        fun onDestroy()
    }
}