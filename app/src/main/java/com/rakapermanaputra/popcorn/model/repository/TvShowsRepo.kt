package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.*
import io.reactivex.Flowable

interface TvShowsRepo {

    fun getAiringToday(): Flowable<TvShowsResponse>

    fun getOnTheAir(): Flowable<TvShowsResponse>

    fun getPopular(): Flowable<TvShowsResponse>

    fun getTopRated(): Flowable<TvShowsResponse>

    fun getDiscoverTvShows(): Flowable<TvShowsResponse>

    fun getDetailTv(id: Int): Flowable<TvShowsDetail>

    fun getActors(id: Int): Flowable<CastResponse>

    fun getMovieCredits(id: Int): Flowable<CreditsResponse>

    fun getTvCredits(id: Int): Flowable<CreditsResponse>

    fun postFavTv(accoundId: Int, sessionId: String, reqFavBody: ReqFavBody): Flowable<AddResponse>

    fun getFavTv(accountId: Int, sessionId: String): Flowable<TvShowsResponse>

    fun getTvState(tvId: Int, sessionId: String): Flowable<AccountStateResponse>

    fun getWatchlistTv(accountId: Int, sessionId: String): Flowable<TvShowsResponse>

    fun postWatchlistTv(accountId: Int, sessionId: String, reqWatchlistBody: ReqWatchlistBody): Flowable<AddResponse>


}