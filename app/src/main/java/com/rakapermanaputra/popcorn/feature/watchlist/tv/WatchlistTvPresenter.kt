package com.rakapermanaputra.popcorn.feature.watchlist.tv

import com.rakapermanaputra.popcorn.model.TvShowsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class WatchlistTvPresenter(private val view: WatchlistTvContract.View,
                           private val tvShowsRepoImpl: TvShowsRepoImpl)
    : WatchlistTvContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getAllWatchlist(accountId: Int, sessionId: String) {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getWatchlistTv(accountId, sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsResponse) {
                    view.showWatchlistTv(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}