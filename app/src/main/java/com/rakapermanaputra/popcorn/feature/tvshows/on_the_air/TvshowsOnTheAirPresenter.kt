package com.rakapermanaputra.popcorn.feature.tvshows.on_the_air

import com.rakapermanaputra.popcorn.model.TvShowsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class TvshowsOnTheAirPresenter(private val view: TvshowsOnTheAirContract.View,
                               private val tvShowsRepoImpl: TvShowsRepoImpl)
    : TvshowsOnTheAirContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getOnTheAir() {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getOnTheAir()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsResponse) {
                    view.showOnTheAir(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showOnTheAir(Collections.emptyList())
                }

            })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}