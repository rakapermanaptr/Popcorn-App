package com.rakapermanaputra.popcorn.feature.detail.detail_people.tvshows

import com.rakapermanaputra.popcorn.model.Credits

interface TvCreditsContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showTvCredits(tvCredits: List<Credits>)
    }

    interface Presenter {
        fun getTvCredits(id: Int)
        fun onDestroy()
    }
}