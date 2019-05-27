package com.rakapermanaputra.popcorn.feature.discover.tvshows

import com.rakapermanaputra.popcorn.model.TvShows

interface DiscoverTvshowsContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDiscoverTvshows(discover: List<TvShows>)
    }

    interface Presenter {
        fun getDiscoverTvshows()
        fun onDestroy()
    }
}