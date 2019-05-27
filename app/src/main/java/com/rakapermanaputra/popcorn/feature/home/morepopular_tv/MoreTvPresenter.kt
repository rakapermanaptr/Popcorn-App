package com.rakapermanaputra.popcorn.feature.home.morepopular_tv

import com.rakapermanaputra.popcorn.model.TvShowsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import org.jetbrains.anko.Android
import java.util.*

class MoreTvPresenter(private val view: MoreTvContract.View,
                      private val tvShowsRepoImpl: TvShowsRepoImpl)
    : MoreTvContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getMorePopularTv() {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getPopular()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsResponse) {
                    view.showMorePopularTv(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showMorePopularTv(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}