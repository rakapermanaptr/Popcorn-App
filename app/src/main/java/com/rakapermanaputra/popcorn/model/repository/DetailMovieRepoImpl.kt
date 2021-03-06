package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.network.ApiRest
import io.reactivex.Flowable

class DetailMovieRepoImpl(private val apiRest: ApiRest) : DetailMovieRepo {
    override fun postWatchlistMovie(accountId: Int, sessionId: String, reqWatchlistBody: ReqWatchlistBody): Flowable<AddResponse> =
        apiRest.postWatchlist(accountId, sessionId, reqWatchlistBody)

    override fun getMovieState(moviedId: Int, sessionId: String): Flowable<AccountStateResponse> =
        apiRest.getMovieState(moviedId, sessionId)

    override fun postFavMovie(accountId: Int, sessionId: String, reqFavBody: ReqFavBody): Flowable<AddResponse> =
        apiRest.postFavorite(accountId, sessionId, reqFavBody)

    override fun getReview(id: Int): Flowable<ReviewResponse> = apiRest.getReview(id)

    override fun getCaster(id: Int): Flowable<CastResponse> = apiRest.getCaster(id)

    override fun getRecommendationMovies(id: Int): Flowable<RecommendationMovieResponse> =
        apiRest.getRecommendationMovies(id)

    override fun getSimilarMovies(id: Int): Flowable<SimilarMovieResponse> = apiRest.getSimilarMovies(id)

    override fun getDetailMovie(id: Int): Flowable<DetailMovie> = apiRest.getDetailMovie(id)

}