package com.rakapermanaputra.popcorn.model.repository

interface LocalRepo {
    fun insertFavMovie(movieId: Int)
    fun deleteFavMovie(movieId: Int)
}