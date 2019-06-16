package com.rakapermanaputra.popcorn.feature.detail.detail_movie

import android.util.Log
import com.rakapermanaputra.popcorn.model.AddFavResponse
import com.rakapermanaputra.popcorn.model.DetailMovie
import com.rakapermanaputra.popcorn.model.ReqFavBody
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.model.repository.LocalRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class DetailMoviePresenter(private val view: DetailContract.View,
                           private val detailMovieRepoImpl: DetailMovieRepoImpl,
                           private val localRepoImpl: LocalRepoImpl)
    : DetailContract.Presenter {
    override fun insertFavorite(movieId: Int) {
        localRepoImpl.insertFavMovie(movieId)
    }

    override fun deleteFavorite(movieId: Int) {
        localRepoImpl.deleteFavMovie(movieId)
    }


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

    override fun postFavMovie(accounId: Int, sessionId: String, reqFavBody: ReqFavBody) {
        compositeDisposable.add(detailMovieRepoImpl.postFavMovie(accounId, sessionId, reqFavBody)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<AddFavResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: AddFavResponse) {
                    view.hideLoading()
                    view.showMessage(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error Post", "error : " + t?.message)
                }
            }))
    }


    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}