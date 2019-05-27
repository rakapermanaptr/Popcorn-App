package com.rakapermanaputra.popcorn.feature.tvshows.top_rated

import com.rakapermanaputra.popcorn.model.TvShows

interface TvshowsTopRatedContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showTopRated(tvshows: List<TvShows>)
    }

    interface Presenter {
        fun getTopRated()
        fun onDestroy()
    }
}