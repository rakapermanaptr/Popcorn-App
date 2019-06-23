package com.rakapermanaputra.popcorn.feature.detail.detail_tv

import com.rakapermanaputra.popcorn.model.*

interface DetailTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDetail(dataDetail: TvShowsDetail)
        fun markFavorite(addFavResponse: AddFavResponse)
        fun showFavoriteState(state: Boolean)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun postFavTv(accoundId: Int, sessionId: String, reqFavBody: ReqFavBody)
        fun getTvState(tvId: Int, sessionId: String)
        fun onDestroy()
    }
}