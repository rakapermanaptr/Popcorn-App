package com.rakapermanaputra.popcorn.feature.detail.detail_people.tvshows

import com.rakapermanaputra.popcorn.model.CreditsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class TvCreditsPresenter(private val view: TvCreditsContract.View,
                         private val tvShowsRepoImpl: TvShowsRepoImpl)
    : TvCreditsContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getTvCredits(id: Int) {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getTvCredits(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<CreditsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: CreditsResponse) {
                    view.showTvCredits(t.cast)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showTvCredits(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}