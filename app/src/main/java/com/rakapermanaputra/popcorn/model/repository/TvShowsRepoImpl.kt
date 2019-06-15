package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.network.ApiRest
import io.reactivex.Flowable

class TvShowsRepoImpl(private val apiRest: ApiRest) : TvShowsRepo {
    override fun getFavTv(accountId: Int, sessionId: String): Flowable<TvShowsResponse> = apiRest.getFavoriteTv(accountId, sessionId)
    override fun postFavTv(accoundId: Int, sessionId: String, reqFavBody: ReqFavBody) = apiRest.postFavorite(accoundId, sessionId, reqFavBody)

    override fun getTvCredits(id: Int): Flowable<CreditsResponse> = apiRest.getTvCredits(id)

    override fun getMovieCredits(id: Int): Flowable<CreditsResponse> = apiRest.getMovieCredits(id)

    override fun getActors(id: Int): Flowable<CastResponse> = apiRest.getTvActors(id)

    override fun getDetailTv(id: Int): Flowable<TvShowsDetail> = apiRest.getDetailTv(id)

    override fun getDiscoverTvShows(): Flowable<TvShowsResponse> = apiRest.getDiscoverTvShows()

    override fun getTopRated(): Flowable<TvShowsResponse> = apiRest.getTopRatedTvShows()

    override fun getPopular(): Flowable<TvShowsResponse> = apiRest.getPopularTvShows()

    override fun getOnTheAir(): Flowable<TvShowsResponse> = apiRest.getOnTheAirTvShows()

    override fun getAiringToday(): Flowable<TvShowsResponse> = apiRest.getAiringTodayTvShows()

}