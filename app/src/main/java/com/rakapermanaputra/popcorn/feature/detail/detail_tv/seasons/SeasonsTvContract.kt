package com.rakapermanaputra.popcorn.feature.detail.detail_tv.seasons

import com.rakapermanaputra.popcorn.model.Season

interface SeasonsTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showSeasons(season: List<Season>)
    }

    interface Presenter{
        fun getTvSeasons(id: Int)
        fun onDestroy()
    }
}