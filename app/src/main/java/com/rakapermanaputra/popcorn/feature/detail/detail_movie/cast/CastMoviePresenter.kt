package com.rakapermanaputra.popcorn.feature.detail.detail_movie.cast

import com.rakapermanaputra.popcorn.model.CastResponse
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class CastMoviePresenter(private val view: CastMovieContract.View,
                         private val detailMovieRepoImpl: DetailMovieRepoImpl)
    : CastMovieContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getCaster(id: Int) {
        view.showLoading()
        compositeDisposable.add(detailMovieRepoImpl.getCaster(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<CastResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: CastResponse) {
                    view.showCaster(t.cast)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showCaster(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}