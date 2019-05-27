package com.rakapermanaputra.popcorn.feature.discover.tvshows

import com.rakapermanaputra.popcorn.model.TvShowsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class DiscoverTvshowsPresenter(private val view: DiscoverTvshowsContract.View,
                               private val tvShowsRepoImpl: TvShowsRepoImpl)
    : DiscoverTvshowsContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getDiscoverTvshows() {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getDiscoverTvShows()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsResponse) {
                    view.showDiscoverTvshows(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showDiscoverTvshows(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}