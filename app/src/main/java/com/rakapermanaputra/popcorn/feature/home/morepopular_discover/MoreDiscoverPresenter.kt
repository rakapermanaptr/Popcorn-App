package com.rakapermanaputra.popcorn.feature.home.morepopular_discover

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class MoreDiscoverPresenter(private val view: MoreDiscoverContract.View,
                            private val movieRepoImpl: MoviesRepoImpl)
    : MoreDiscoverContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getMoreDiscover() {
        view.showLoading()
        compositeDisposable.add(movieRepoImpl.getDiscoverMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: MoviesResponse) {
                    view.showMoreDiscover(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showMoreDiscover(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}