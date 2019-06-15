package com.rakapermanaputra.popcorn.feature.favorite.tv

import android.util.Log
import com.rakapermanaputra.popcorn.model.TvShowsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class FavTvPresenter(private val view: FavTvContract.View,
                     private val tvShowsRepoImpl: TvShowsRepoImpl)
    : FavTvContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getFavoriteTv(accountId: Int, sessionId: String) {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getFavTv(accountId, sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsResponse?) {
                    view.hideLoading()
                    view.showFavTv(t?.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showFavTv(Collections.emptyList())
                    Log.e("Data", "Error get fav tv : " + t?.message)
                }

            }))
    }
}