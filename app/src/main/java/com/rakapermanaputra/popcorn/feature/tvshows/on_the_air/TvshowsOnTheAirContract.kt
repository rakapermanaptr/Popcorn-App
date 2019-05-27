package com.rakapermanaputra.popcorn.feature.tvshows.on_the_air

import com.rakapermanaputra.popcorn.model.TvShows

interface TvshowsOnTheAirContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showOnTheAir(tvshows: List<TvShows>)
    }

    interface Presenter {
        fun getOnTheAir()
        fun onDestroy()
    }
}