package com.rakapermanaputra.popcorn.feature.detail.detail_tv.seasons

import android.util.Log
import com.rakapermanaputra.popcorn.model.TvShowsDetail
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class SeasonsTvPresenter(private val view: SeasonsTvContract.View,
                         private val tvShowsRepoImpl: TvShowsRepoImpl) : SeasonsTvContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getTvSeasons(id: Int) {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getDetailTv(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsDetail>() {
                override fun onComplete() {
                    view.hideLoading()
                    Log.i("Success", "Load data")
                }

                override fun onNext(t: TvShowsDetail) {
                    view.showSeasons(t.seasons)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showSeasons(Collections.emptyList())
                    Log.e("Error", t?.message)
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}