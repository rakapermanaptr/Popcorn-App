package com.rakapermanaputra.popcorn.feature.movies.top_rated

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class MoviesTopRatedPresenter(private val view: MoviesTopRatedContract.View,
                              private val moviesRepoImpl: MoviesRepoImpl)
    : MoviesTopRatedContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getTopRated() {
        view.showLoading()
        compositeDisposable.add(moviesRepoImpl.getTopRated()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>(){
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: MoviesResponse) {
                    view.showTopRated(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showTopRated(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}