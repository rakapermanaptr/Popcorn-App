package com.rakapermanaputra.popcorn.feature.detail.detail_movie

import android.util.Log
import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class DetailMoviePresenter(private val view: DetailContract.View,
                           private val detailMovieRepoImpl: DetailMovieRepoImpl)
    : DetailContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getDetail(id: Int) {
        view.showLoading()
        compositeDisposable.add(detailMovieRepoImpl.getDetailMovie(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<DetailMovie>() {
                override fun onComplete() {
                    view.hideLoading()
                }
                override fun onNext(t: DetailMovie) {
                    view.showDetail(t)
                    view.showBackdrop(t)
                    view.showPoster(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error", t?.message)
                }

            }))
    }

    override fun postFavorite(accounId: Int, sessionId: String, reqFavBody: ReqFavBody) {
        compositeDisposable.add(detailMovieRepoImpl.postFavMovie(accounId, sessionId, reqFavBody)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<AddResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: AddResponse) {
                    view.hideLoading()
                    view.markFavorite(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error Post", "error : " + t?.message)
                }
            }))
    }

    override fun postWatchlist(accounId: Int, sessionId: String, reqWatchlistBody: ReqWatchlistBody) {
        compositeDisposable.add(detailMovieRepoImpl.postWatchlistMovie(accounId, sessionId, reqWatchlistBody)
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

    override fun getMovieState(movieId: Int, sessionId: String) {
        compositeDisposable.add(detailMovieRepoImpl.getMovieState(movieId, sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<AccountStateResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: AccountStateResponse) {
                    view.showMovieState(t.favorite, t.watchlist)
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