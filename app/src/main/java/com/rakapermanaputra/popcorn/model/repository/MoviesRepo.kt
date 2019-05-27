package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.MoviesResponse
import io.reactivex.Flowable

interface MoviesRepo {

    fun getNowPlaying(): Flowable<MoviesResponse>

    fun getPopular(): Flowable<MoviesResponse>

    fun getTopRated(): Flowable<MoviesResponse>

    fun getUpcoming(): Flowable<MoviesResponse>

    fun getDiscoverMovies(): Flowable<MoviesResponse>

//    fun getDetailMovie(id: String): Flowable<DetailMovie>
}