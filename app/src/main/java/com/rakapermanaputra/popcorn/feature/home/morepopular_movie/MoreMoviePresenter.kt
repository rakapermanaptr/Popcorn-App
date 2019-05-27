package com.rakapermanaputra.popcorn.feature.home.morepopular_movie

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class MoreMoviePresenter(private val view: MoreMovieContract.View,
                         private val moviesRepoImpl: MoviesRepoImpl)
    : MoreMovieContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getMorePopularMovie() {
        view.showLoading()
        compositeDisposable.add(moviesRepoImpl.getPopular()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: MoviesResponse) {
                    view.showMorePopularMovie(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showMorePopularMovie(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}