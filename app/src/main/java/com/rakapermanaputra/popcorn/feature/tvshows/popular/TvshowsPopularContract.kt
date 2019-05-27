package com.rakapermanaputra.popcorn.feature.tvshows.popular

import com.rakapermanaputra.popcorn.model.TvShows

interface TvshowsPopularContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showPopular(tvshows: List<TvShows>)
    }

    interface Presenter {
        fun getPopular()
        fun onDestroy()
    }
}