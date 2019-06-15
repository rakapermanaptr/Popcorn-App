package com.rakapermanaputra.popcorn.feature.favorite

import com.rakapermanaputra.popcorn.model.Movies

interface FavoriteContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showFavMovie(movies: List<Movies>?)
    }

    interface Presenter {
        fun getFavoriteMovies(accountId: Int, sessionId: String)
    }
}