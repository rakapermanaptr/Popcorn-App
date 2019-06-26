package com.rakapermanaputra.popcorn.feature.detail.detail_movie

import com.rakapermanaputra.popcorn.model.AddResponse
import com.rakapermanaputra.popcorn.model.DetailMovie
import com.rakapermanaputra.popcorn.model.ReqFavBody
import com.rakapermanaputra.popcorn.model.ReqWatchlistBody

interface DetailContract {

    interface View {
        fun showBackdrop(detailMovie: DetailMovie)
        fun showPoster(detailMovie: DetailMovie)
        fun showDetail(detailMovie: DetailMovie)
        fun showLoading()
        fun hideLoading()
        fun markFavorite(addResponse: AddResponse)
        fun markWatchlist(addResponse: AddResponse)
        fun showMovieState(stateFavorite: Boolean, stateWatchlist: Boolean)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun postFavorite(accounId: Int, sessionId: String, reqFavBody: ReqFavBody)
        fun postWatchlist(accounId: Int, sessionId: String, reqWatchlistBody: ReqWatchlistBody)
        fun getMovieState(movieId: Int, sessionId: String)
        fun onDestroy()
    }
}