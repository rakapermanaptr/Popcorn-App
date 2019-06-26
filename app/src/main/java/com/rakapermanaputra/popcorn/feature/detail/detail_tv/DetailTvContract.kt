package com.rakapermanaputra.popcorn.feature.detail.detail_tv

import com.rakapermanaputra.popcorn.model.*

interface DetailTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDetail(dataDetail: TvShowsDetail)
        fun markFavorite(addResponse: AddResponse)
        fun markWatchlist(addResponse: AddResponse)
        fun showTvState(stateFavorite: Boolean, stateWatchlist: Boolean)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun postFavorite(accoundId: Int, sessionId: String, reqFavBody: ReqFavBody)
        fun postWatchlist(accounId: Int, sessionId: String, reqWatchlistBody: ReqWatchlistBody)
        fun getTvState(tvId: Int, sessionId: String)
        fun onDestroy()
    }
}