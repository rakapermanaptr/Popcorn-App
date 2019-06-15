package com.rakapermanaputra.popcorn.feature.favorite.tv

import com.rakapermanaputra.popcorn.model.ReqFavBody
import com.rakapermanaputra.popcorn.model.TvShows

interface FavTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showFavTv(tvShows: List<TvShows>?)
    }

    interface Presenter {
        fun getFavoriteTv(accountId: Int, sessionId: String)
    }
}