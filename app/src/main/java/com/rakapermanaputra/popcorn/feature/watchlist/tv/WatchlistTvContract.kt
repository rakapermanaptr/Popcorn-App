package com.rakapermanaputra.popcorn.feature.watchlist.tv

import com.rakapermanaputra.popcorn.model.TvShows

interface WatchlistTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showWatchlistTv(tvShows: List<TvShows>)
    }

    interface Presenter {
        fun getAllWatchlist(accountId: Int, sessionId: String)
        fun onDestroy()
    }
}