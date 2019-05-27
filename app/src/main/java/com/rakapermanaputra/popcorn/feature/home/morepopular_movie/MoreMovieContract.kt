package com.rakapermanaputra.popcorn.feature.home.morepopular_movie

import com.rakapermanaputra.popcorn.model.Movies

interface MoreMovieContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMorePopularMovie(morePopular: List<Movies>)
    }

    interface Presenter {
        fun getMorePopularMovie()
        fun onDestroy()
    }
}