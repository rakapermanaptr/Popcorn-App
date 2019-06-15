package com.rakapermanaputra.popcorn.feature.detail.detail_tv

import android.util.Log
import com.rakapermanaputra.popcorn.model.AddFavResponse
import com.rakapermanaputra.popcorn.model.DetailTv
import com.rakapermanaputra.popcorn.model.ReqFavBody
import com.rakapermanaputra.popcorn.model.TvShowsDetail
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

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

    override fun postFavTv(accoundId: Int, sessionId: String, reqFavBody: ReqFavBody) {
        compositeDisposable.add(tvShowsRepoImpl.postFavTv(accoundId, sessionId, reqFavBody)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<AddFavResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: AddFavResponse) {
                    view.showMessage(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error", "Add favorite tv error : " + t?.message)
                }

            }))
    }


    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}