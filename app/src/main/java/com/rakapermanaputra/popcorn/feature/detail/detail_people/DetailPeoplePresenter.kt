package com.rakapermanaputra.popcorn.feature.detail.detail_people

import android.util.Log
import com.rakapermanaputra.popcorn.model.PeopleDetail
import com.rakapermanaputra.popcorn.model.repository.PeopleRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class DetailPeoplePresenter(private val view: DetailPeopleContract.View,
                            private val peopleRepoImpl: PeopleRepoImpl)
    : DetailPeopleContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getDetail(id: Int) {
        view.showLoading()
        compositeDisposable.add(peopleRepoImpl.getDetailPeople(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<PeopleDetail>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: PeopleDetail) {
                    view.showDetail(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error", t?.message)
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}