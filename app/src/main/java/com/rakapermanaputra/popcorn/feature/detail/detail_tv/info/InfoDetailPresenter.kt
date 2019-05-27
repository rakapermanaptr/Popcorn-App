package com.rakapermanaputra.popcorn.feature.detail.detail_tv.info

import android.util.Log
import com.rakapermanaputra.popcorn.model.TvShowsDetail
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class InfoDetailPresenter(private val view: InfoDetailTvContract.View,
                          private val tvShowsRepoImpl: TvShowsRepoImpl)
    : InfoDetailTvContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getInfoDetail(id: Int) {
        compositeDisposable.add(tvShowsRepoImpl.getDetailTv(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsDetail>() {
                override fun onComplete() {
                    Log.i("Success", "Load data")
                }

                override fun onNext(t: TvShowsDetail) {
                    view.showInfoDetail(t)
                }

                override fun onError(t: Throwable?) {
                    Log.e("Error", t?.message)
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}