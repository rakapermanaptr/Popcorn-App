package com.rakapermanaputra.popcorn.feature.search

import android.util.Log
import com.rakapermanaputra.popcorn.model.SearchResponse
import com.rakapermanaputra.popcorn.model.repository.SearchRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class SearchPresenter(private val view: SearchContract.View,
                      private val searchRepoImpl: SearchRepoImpl)
    : SearchContract.Presenter{

    val compositeDisposable = CompositeDisposable()

    override fun getResultSearch(query: String) {
        view.showLoading()
        compositeDisposable.add(searchRepoImpl.getSearch(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<SearchResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: SearchResponse) {
                    view.showResultSearch(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showResultSearch(Collections.emptyList())
                    Log.e("Error", "error search : " + t?.message)
                }

            }))
    }
}