package com.rakapermanaputra.popcorn.feature.home.morepopular_tv

import com.rakapermanaputra.popcorn.model.TvShows

interface MoreTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMorePopularTv(moreTv: List<TvShows>)
    }

    interface Presenter {
        fun getMorePopularTv()
        fun onDestroy()
    }
}