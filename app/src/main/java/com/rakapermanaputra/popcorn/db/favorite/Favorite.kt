package com.rakapermanaputra.popcorn.db.favorite

data class Favorite(val id: Long?,
                    val movieId: Int) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MOVIE_ID: String = "MOVIE_ID"
    }
}