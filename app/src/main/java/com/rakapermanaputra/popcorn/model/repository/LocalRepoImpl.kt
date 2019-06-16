package com.rakapermanaputra.popcorn.model.repository

import android.content.Context
import com.rakapermanaputra.popcorn.db.favorite.Favorite
import com.rakapermanaputra.popcorn.db.favorite.database
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class LocalRepoImpl(private val context: Context): LocalRepo {
    override fun insertFavMovie(movieId: Int) {
        context.database.use {
            insert(Favorite.TABLE_FAVORITE,
                Favorite.MOVIE_ID to movieId)
        }
    }

    override fun deleteFavMovie(movieId: Int) {
        context.database.use {
            delete(Favorite.TABLE_FAVORITE,
                "(MOVIE_ID = {id})",
                "id" to movieId)
        }
    }
}