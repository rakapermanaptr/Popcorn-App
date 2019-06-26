package com.rakapermanaputra.popcorn.feature.watchlist.movie

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class WatchlistMoviePresenter(private val view: WatchlistMovieContract.View,
                              private val movieRepoImpl: MoviesRepoImpl)
    : WatchlistMovieContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getAllWatchlist(accountId: Int, sessionId: String) {
        view.showLoading()
        compositeDisposable.add(movieRepoImpl.getWatchlistMovies(accountId, sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    view.hideLoding()
                }

                override fun onNext(t: MoviesResponse) {
                    view.showAllWatchlist(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoding()
                    view.showAllWatchlist(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}