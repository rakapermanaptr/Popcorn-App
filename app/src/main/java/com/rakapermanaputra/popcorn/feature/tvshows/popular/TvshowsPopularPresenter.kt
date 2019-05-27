package com.rakapermanaputra.popcorn.feature.tvshows.popular

import com.rakapermanaputra.popcorn.model.TvShowsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class TvshowsPopularPresenter(private val view: TvshowsPopularContract.View,
                              private val tvShowsRepoImpl: TvShowsRepoImpl)
    : TvshowsPopularContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getPopular() {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getPopular()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsResponse) {
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