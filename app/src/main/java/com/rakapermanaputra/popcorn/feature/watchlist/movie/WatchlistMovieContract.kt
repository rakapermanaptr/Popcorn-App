package com.rakapermanaputra.popcorn.feature.watchlist.movie

import com.rakapermanaputra.popcorn.model.Movies

interface WatchlistMovieContract {

    interface View {
        fun showLoading()
        fun hideLoding()
        fun showAllWatchlist(movies: List<Movies>)
    }

    interface Presenter {
        fun getAllWatchlist(accountId: Int, sessionId: String)
        fun onDestroy()
    }
}