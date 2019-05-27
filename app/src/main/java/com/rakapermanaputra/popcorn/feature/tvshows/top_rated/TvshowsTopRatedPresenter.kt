package com.rakapermanaputra.popcorn.feature.tvshows.top_rated

import com.rakapermanaputra.popcorn.model.TvShowsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class TvshowsTopRatedPresenter(private val view: TvshowsTopRatedContract.View,
                               private val tvShowsRepoImpl: TvShowsRepoImpl)
    : TvshowsTopRatedContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getTopRated() {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getTopRated()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsResponse) {
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