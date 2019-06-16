package com.rakapermanaputra.popcorn.db.favorite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.MOVIE_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }


}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)