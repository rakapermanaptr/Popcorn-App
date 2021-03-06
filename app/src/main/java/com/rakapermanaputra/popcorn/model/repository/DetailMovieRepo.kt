package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.*
import io.reactivex.Flowable

interface DetailMovieRepo {

    fun getDetailMovie(id: Int): Flowable<DetailMovie>

    fun getSimilarMovies(id: Int) : Flowable<SimilarMovieResponse>

    fun getRecommendationMovies(id: Int) : Flowable<RecommendationMovieResponse>

    fun getCaster(id: Int) : Flowable<CastResponse>

    fun getReview(id: Int) : Flowable<ReviewResponse>

    fun postFavMovie(accountId: Int, sessionId: String, reqFavBody: ReqFavBody) : Flowable<AddResponse>

    fun getMovieState(moviedId: Int, sessionId: String): Flowable<AccountStateResponse>

    fun postWatchlistMovie(accountId: Int, sessionId: String, reqWatchlistBody: ReqWatchlistBody): Flowable<AddResponse>

}