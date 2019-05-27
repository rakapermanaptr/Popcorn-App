package com.rakapermanaputra.popcorn.feature.detail.detail_people.info

import android.util.Log
import com.rakapermanaputra.popcorn.model.ImagesPeopleResponse
import com.rakapermanaputra.popcorn.model.PeopleDetail
import com.rakapermanaputra.popcorn.model.repository.PeopleRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class InfoPeoplePresenter(private val view: InfoPeopleContract.View,
                          private val peopleRepoImpl: PeopleRepoImpl)
    : InfoPeopleContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getImages(id: Int) {
        compositeDisposable.add(peopleRepoImpl.getImagesPeople(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<ImagesPeopleResponse>() {
                override fun onComplete() {
                    Log.i("Data", "Complete image")
                }

                override fun onNext(t: ImagesPeopleResponse) {
                    view.showImages(t.profiles)
                }

                override fun onError(t: Throwable?) {
                    view.showImages(Collections.emptyList())
                    Log.e("Error image", t?.message)
                }

            }))
    }

    override fun getDetail(id: Int) {
        compositeDisposable.add(peopleRepoImpl.getDetailPeople(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<PeopleDetail>() {
                override fun onComplete() {
                    Log.i("Data ","Complete")
                }

                override fun onNext(t: PeopleDetail) {
                    view.showDetail(t)
                }

                override fun onError(t: Throwable?) {
                    Log.e("Data ","Error")
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}