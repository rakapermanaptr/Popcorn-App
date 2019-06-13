package com.rakapermanaputra.popcorn.feature.home

import com.rakapermanaputra.popcorn.model.*

interface HomeFragmentContract {

    interface View {
        fun showNowPlayingMovies(npMovies: List<Movies>)
        fun showPopularMovies(popularMovies: List<Movies>)
        fun showPopularTvs(popularTvs: List<TvShows>)
        fun showDiscoverMovies(discoverMovies: List<Movies>)
    }

    interface Presenter {
        fun getNowPlayingMovies()
        fun getPopularMovies()
        fun getPopularTvs()
        fun getDiscoverMovies()
        fun onDestroy()
    }
}