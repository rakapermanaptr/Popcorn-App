package com.rakapermanaputra.popcorn.feature.tvshows.airing_today

import com.rakapermanaputra.popcorn.model.TvShowsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class TvshowsAiringTodayPresenter(private val view: TvshowsAiringTodayContract.View,
                                  private val tvShowsRepoImpl: TvShowsRepoImpl)
    : TvshowsAiringTodayContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getAiringToday() {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getAiringToday()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsResponse) {
                    view.showAiringToday(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showAiringToday(Collections.emptyList())
                }

            })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}