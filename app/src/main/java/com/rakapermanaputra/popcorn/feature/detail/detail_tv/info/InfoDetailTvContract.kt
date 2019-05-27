package com.rakapermanaputra.popcorn.feature.detail.detail_tv.info

import com.rakapermanaputra.popcorn.model.TvShowsDetail

interface InfoDetailTvContract {

    interface View {
        fun showInfoDetail(dataDetail: TvShowsDetail)
    }

    interface Presenter {
        fun getInfoDetail(id: Int)
        fun onDestroy()
    }
}