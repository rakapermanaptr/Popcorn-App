package com.rakapermanaputra.popcorn.feature.popular_people

import com.rakapermanaputra.popcorn.model.PeopleResponse
import com.rakapermanaputra.popcorn.model.repository.PeopleRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class PopularPeoplePresenter(private val view:PopularPeopleContract.View,
                             private val peopleRepoImpl: PeopleRepoImpl)
    : PopularPeopleContract.Presenter{

    val compositeDisposable = CompositeDisposable()

    override fun getPopularPeoples() {
        view.showLoading()
        compositeDisposable.add(peopleRepoImpl.getPopularPeoples()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<PeopleResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: PeopleResponse) {
                    view.showPopularPeoples(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showPopularPeoples(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}