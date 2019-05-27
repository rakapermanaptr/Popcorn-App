package com.rakapermanaputra.popcorn.feature.detail.detail_tv

import com.rakapermanaputra.popcorn.model.DetailTv
import com.rakapermanaputra.popcorn.model.TvShowsDetail

interface DetailTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDetail(dataDetail: TvShowsDetail)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun onDestroy()
    }
}