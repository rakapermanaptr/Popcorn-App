package com.rakapermanaputra.popcorn.feature.tvshows.airing_today

import com.rakapermanaputra.popcorn.model.TvShows

interface TvshowsAiringTodayContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showAiringToday(tvshows: List<TvShows>)
    }

    interface Presenter {
        fun getAiringToday()
        fun onDestroy()
    }
}