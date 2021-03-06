package com.rakapermanaputra.popcorn.network

import com.rakapermanaputra.popcorn.BuildConfig
import com.rakapermanaputra.popcorn.model.*
import io.reactivex.Flowable
import retrofit2.http.*

interface ApiRest {

    @GET("movie/now_playing" + BuildConfig.API_KEY)
    fun getNowPlayingMovies(): Flowable<MoviesResponse>

    @GET("movie/popular" + BuildConfig.API_KEY)
    fun getPopularMovies(): Flowable<MoviesResponse>

    @GET("movie/top_rated" + BuildConfig.API_KEY)
    fun getTopRatedMovies(): Flowable<MoviesResponse>

    @GET("movie/upcoming" + BuildConfig.API_KEY)
    fun getUpcomingMovies(): Flowable<MoviesResponse>

    @GET("tv/airing_today" + BuildConfig.API_KEY)
    fun getAiringTodayTvShows(): Flowable<TvShowsResponse>

    @GET("tv/on_the_air" + BuildConfig.API_KEY)
    fun getOnTheAirTvShows(): Flowable<TvShowsResponse>

    @GET("tv/popular" + BuildConfig.API_KEY)
    fun getPopularTvShows(): Flowable<TvShowsResponse>

    @GET("tv/top_rated" + BuildConfig.API_KEY)
    fun getTopRatedTvShows(): Flowable<TvShowsResponse>

    @GET("discover/movie" + BuildConfig.API_KEY)
    fun getDiscoverMovie(): Flowable<MoviesResponse>

    @GET("discover/tv" + BuildConfig.API_KEY)
    fun getDiscoverTvShows(): Flowable<TvShowsResponse>

    @GET("person/popular" + BuildConfig.API_KEY)
    fun getPopularPeoples(): Flowable<PeopleResponse>

    @GET("movie/{movie_id}" + BuildConfig.API_KEY)
    fun getDetailMovie(@Path("movie_id") id: Int): Flowable<DetailMovie>

    @GET("movie/{movie_id}/similar" + BuildConfig.API_KEY)
    fun getSimilarMovies(@Path("movie_id") id: Int): Flowable<SimilarMovieResponse>

    @GET("movie/{movie_id}/recommendations" + BuildConfig.API_KEY)
    fun getRecommendationMovies(@Path("movie_id") id: Int): Flowable<RecommendationMovieResponse>

    @GET("movie/{movie_id}/credits" + BuildConfig.API_KEY)
    fun getCaster(@Path("movie_id") id: Int): Flowable<CastResponse>

    @GET("movie/{movie_id}/reviews" + BuildConfig.API_KEY)
    fun getReview(@Path("movie_id") id: Int): Flowable<ReviewResponse>

    @GET("tv/{tv_id}" + BuildConfig.API_KEY)
    fun getDetailTv(@Path("tv_id") id: Int): Flowable<TvShowsDetail>

    @GET("tv/{tv_id}/credits" + BuildConfig.API_KEY)
    fun getTvActors(@Path("tv_id") id: Int): Flowable<CastResponse>

    @GET("person/{person_id}" + BuildConfig.API_KEY)
    fun getDetailPeople(@Path("person_id") id: Int): Flowable<PeopleDetail>

    @GET("person/{person_id}/movie_credits" + BuildConfig.API_KEY)
    fun getMovieCredits(@Path("person_id") id: Int): Flowable<CreditsResponse>

    @GET("person/{person_id}/tv_credits" + BuildConfig.API_KEY)
    fun getTvCredits(@Path("person_id") id: Int): Flowable<CreditsResponse>

    @GET("person/{person_id}/images" + BuildConfig.API_KEY)
    fun getImagesPeople(@Path("person_id") id: Int): Flowable<ImagesPeopleResponse>

    @GET("authentication/token/new" + BuildConfig.API_KEY)
    fun getReqToken(): Flowable<Token>

    @POST("authentication/token/validate_with_login" + BuildConfig.API_KEY)
    fun getToken(@Body login: Login): Flowable<Token>

    @POST("authentication/session/new" + BuildConfig.API_KEY)
    fun getSession(@Body requestToken: RequestToken): Flowable<Session>

    @GET("account" + BuildConfig.API_KEY)
    fun getAccount(@Query("session_id") sessionId: String): Flowable<Account>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("account/{account_id}/favorite" + BuildConfig.API_KEY)
    fun postFavorite(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Body reqFavBody: ReqFavBody
    ): Flowable<AddResponse>

    @GET("account/{account_id}/favorite/movies" + BuildConfig.API_KEY)
    fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String
    ): Flowable<MoviesResponse>

    @GET("account/{account_id}/favorite/tv" + BuildConfig.API_KEY)
    fun getFavoriteTv(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String
    ): Flowable<TvShowsResponse>

    @GET("movie/{movie_id}/account_states" + BuildConfig.API_KEY)
    fun getMovieState(
        @Path("movie_id") movieId: Int,
        @Query("session_id") sessionId: String
    ): Flowable<AccountStateResponse>

    @GET("tv/{tv_id}/account_states" + BuildConfig.API_KEY)
    fun getTvState(
        @Path("tv_id") tvId: Int,
        @Query("session_id") sessionId: String
    ): Flowable<AccountStateResponse>

    @GET("search/multi" + BuildConfig.API_KEY)
    fun getSearch(@Query("query") query: String): Flowable<SearchResponse>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("account/{account_id}/watchlist" + BuildConfig.API_KEY)
    fun postWatchlist(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Body reqWatchlistBody: ReqWatchlistBody
    ): Flowable<AddResponse>

    @GET("account/{account_id}/watchlist/movies" + BuildConfig.API_KEY)
    fun getWatchlistMovies(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String
    ): Flowable<MoviesResponse>

    @GET("account/{account_id}/watchlist/tv" + BuildConfig.API_KEY)
    fun getWatchlistTv(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String
    ): Flowable<TvShowsResponse>
}