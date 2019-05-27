package com.rakapermanaputra.popcorn.feature.discover.movie

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class DiscoverMoviesPresenter(private val view: DiscoverMoviesContract.View,
                              private val moviesRepoImpl: MoviesRepoImpl)
    : DiscoverMoviesContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getDiscoverMovie() {
        view.showLoading()
        compositeDisposable.add(moviesRepoImpl.getDiscoverMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: MoviesResponse) {
                    view.showDiscoverMovies(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showDiscoverMovies(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}