package com.rakapermanaputra.popcorn.feature.detail.detail_tv

import android.util.Log
import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class DetailTvPresenter(private val view: DetailTvContract.View,
                        private val tvShowsRepoImpl: TvShowsRepoImpl)
    : DetailTvContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getDetail(id: Int) {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getDetailTv(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsDetail>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: TvShowsDetail) {
                    view.showDetail(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error", t?.message)
                }


            }))
    }

    override fun postFavorite(accoundId: Int, sessionId: String, reqFavBody: ReqFavBody) {
        compositeDisposable.add(tvShowsRepoImpl.postFavTv(accoundId, sessionId, reqFavBody)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<AddResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: AddResponse) {
                    view.markFavorite(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error", "Add favorite tv error : " + t?.message)
                }

            }))
    }

    override fun postWatchlist(accounId: Int, sessionId: String, reqWatchlistBody: ReqWatchlistBody) {
        compositeDisposable.add(tvShowsRepoImpl.postWatchlistTv(accounId, sessionId, reqWatchlistBody)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<AddResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: AddResponse) {
                    view.markWatchlist(t)
                }

                override fun onError(t: Throwable) {
                    view.hideLoading()
                }

            }))
    }

    override fun getTvState(tvId: Int, sessionId: String) {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getTvState(tvId, sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<AccountStateResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: AccountStateResponse) {
                    view.showTvState(t.favorite, t.watchlist)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error", "Error account state : " + t?.message)
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}