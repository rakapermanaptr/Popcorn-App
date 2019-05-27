package com.rakapermanaputra.popcorn.feature.movies.popular

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class MoviesPopularPresenter(private val view: MoviesPopularContract.View,
                             private val moviesRepoImpl: MoviesRepoImpl)
    : MoviesPopularContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getPopular() {
        view.showLoading()
        compositeDisposable.add(moviesRepoImpl.getPopular()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: MoviesResponse) {
                    view.showPopular(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showPopular(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}