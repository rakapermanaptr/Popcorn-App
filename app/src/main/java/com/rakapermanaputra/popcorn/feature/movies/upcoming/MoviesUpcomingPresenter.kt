package com.rakapermanaputra.popcorn.feature.movies.upcoming

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class MoviesUpcomingPresenter(private val view: MoviesUpcomingContract.View,
                              private val moviesRepoImpl: MoviesRepoImpl)
    : MoviesUpcomingContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getUpcoming() {
        view.showLoading()
        compositeDisposable.add(moviesRepoImpl.getUpcoming()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>(){
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: MoviesResponse) {
                    view.showUpcoming(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showUpcoming(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}