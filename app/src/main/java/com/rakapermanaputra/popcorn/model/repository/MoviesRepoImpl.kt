package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.network.ApiRest
import io.reactivex.Flowable

class MoviesRepoImpl(private val apiRest: ApiRest) : MoviesRepo {
    override fun getWatchlistMovies(accountId: Int, sessionId: String): Flowable<MoviesResponse> = apiRest.getWatchlistMovies(accountId, sessionId)

    override fun getFavoriteMovies(accountId: Int, sessionId: String): Flowable<MoviesResponse> = apiRest.getFavoriteMovies(accountId, sessionId)

    override fun getDiscoverMovies(): Flowable<MoviesResponse> = apiRest.getDiscoverMovie()

    override fun getUpcoming(): Flowable<MoviesResponse> = apiRest.getUpcomingMovies()

    override fun getTopRated(): Flowable<MoviesResponse> = apiRest.getTopRatedMovies()

    override fun getPopular(): Flowable<MoviesResponse> = apiRest.getPopularMovies()

    override fun getNowPlaying(): Flowable<MoviesResponse> = apiRest.getNowPlayingMovies()

}