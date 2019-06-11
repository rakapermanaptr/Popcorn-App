package com.rakapermanaputra.popcorn.feature.movies.nowplaying

import com.rakapermanaputra.popcorn.model.Movies

interface MoviesNowPlayingContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showNowPlaying(movies: List<Movies>)
    }

    interface Presenter {
        fun getNowPlaying(page: Int)
        fun onDestroy()
    }
}