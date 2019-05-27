package com.rakapermanaputra.popcorn.feature.movies.upcoming

import com.rakapermanaputra.popcorn.model.Movies

interface MoviesUpcomingContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showUpcoming(movies: List<Movies>)
    }

    interface Presenter {
        fun getUpcoming()
        fun onDestroy()
    }
}