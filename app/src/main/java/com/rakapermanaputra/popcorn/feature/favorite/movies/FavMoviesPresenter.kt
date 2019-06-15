package com.rakapermanaputra.popcorn.feature.favorite.movies

import android.util.Log
import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class FavMoviesPresenter(private val view: FavMoviesContract.View,
                         private val moviesRepoImpl: MoviesRepoImpl)
    : FavMoviesContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getFavoriteMovies(accountId: Int, sessionId: String) {
        view.showLoading()
        compositeDisposable.add(moviesRepoImpl.getFavoriteMovies(accountId, sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: MoviesResponse?) {
                    view.hideLoading()
                    view.showFavMovie(t?.results)
                }

                override fun onError(t: Throwable?) {
                    view.showFavMovie(Collections.emptyList())
                    view.hideLoading()
                    Log.e("Error", "get fav movies error : " + t?.message)
                }

            }))
    }
}