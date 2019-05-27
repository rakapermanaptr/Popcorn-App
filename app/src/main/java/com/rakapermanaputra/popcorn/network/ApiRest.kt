package com.rakapermanaputra.popcorn.network

import com.rakapermanaputra.popcorn.BuildConfig
import com.rakapermanaputra.popcorn.model.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRest {

    @GET("movie/now_playing" + BuildConfig.API_KEY)
    fun getNowPlayingMovies() : Flowable<MoviesResponse>

    @GET("movie/popular" +  BuildConfig.API_KEY)
    fun getPopularMovies() : Flowable<MoviesResponse>

    @GET("movie/top_rated" + BuildConfig.API_KEY)
    fun getTopRatedMovies() : Flowable<MoviesResponse>

    @GET("movie/upcoming" + BuildConfig.API_KEY)
    fun getUpcomingMovies() : Flowable<MoviesResponse>

    @GET("tv/airing_today" + BuildConfig.API_KEY)
    fun getAiringTodayTvShows() : Flowable<TvShowsResponse>

    @GET("tv/on_the_air" + BuildConfig.API_KEY)
    fun getOnTheAirTvShows() : Flowable<TvShowsResponse>

    @GET("tv/popular" + BuildConfig.API_KEY)
    fun getPopularTvShows() : Flowable<TvShowsResponse>

    @GET("tv/top_rated" + BuildConfig.API_KEY)
    fun getTopRatedTvShows() : Flowable<TvShowsResponse>

    @GET("discover/movie" + BuildConfig.API_KEY)
    fun getDiscoverMovie() : Flowable<MoviesResponse>

    @GET("discover/tv" + BuildConfig.API_KEY)
    fun getDiscoverTvShows() : Flowable<TvShowsResponse>

    @GET("person/popular" + BuildConfig.API_KEY)
    fun getPopularPeoples() : Flowable<PeopleResponse>

    @GET("movie/{movie_id}" + BuildConfig.API_KEY)
    fun getDetailMovie(@Path("movie_id") id: Int) : Flowable<DetailMovie>

    @GET("movie/{movie_id}/similar" + BuildConfig.API_KEY)
    fun getSimilarMovies(@Path("movie_id") id: Int) : Flowable<SimilarMovieResponse>

    @GET("movie/{movie_id}/recommendations" + BuildConfig.API_KEY)
    fun getRecommendationMovies(@Path("movie_id") id: Int) : Flowable<RecommendationMovieResponse>

    @GET("movie/{movie_id}/credits" + BuildConfig.API_KEY)
    fun getCaster(@Path("movie_id") id: Int) : Flowable<CastResponse>

    @GET("movie/{movie_id}/reviews" + BuildConfig.API_KEY)
    fun getReview(@Path("movie_id") id: Int) : Flowable<ReviewResponse>

    @GET("tv/{tv_id}" + BuildConfig.API_KEY)
    fun getDetailTv(@Path("tv_id") id: Int) : Flowable<TvShowsDetail>

    @GET("tv/{tv_id}/credits" + BuildConfig.API_KEY)
    fun getTvActors(@Path("tv_id") id: Int) : Flowable<CastResponse>

    @GET("person/{person_id}" + BuildConfig.API_KEY)
    fun getDetailPeople(@Path("person_id") id: Int) :  Flowable<PeopleDetail>

    @GET("person/{person_id}/movie_credits" + BuildConfig.API_KEY)
    fun getMovieCredits(@Path("person_id") id: Int) : Flowable<CreditsResponse>

    @GET("person/{person_id}/tv_credits" + BuildConfig.API_KEY)
    fun getTvCredits(@Path("person_id") id: Int): Flowable<CreditsResponse>

    @GET("person/{person_id}/images" +  BuildConfig.API_KEY)
    fun getImagesPeople(@Path("person_id") id: Int) : Flowable<ImagesPeopleResponse>

//    @GET("movie/now_playing" + BuildConfig.API_KEY)
//    fun getDetailMovie() : Flowable<MoviesResponse>
}