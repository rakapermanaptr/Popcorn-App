package com.rakapermanaputra.popcorn.feature.detail.detail_tv.actors

import com.rakapermanaputra.popcorn.model.CastResponse
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class ActorsTvPresenter(private val view: ActorsTvContract.View,
                        private val tvShowsRepoImpl: TvShowsRepoImpl)
    : ActorsTvContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getActors(id: Int) {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getActors(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<CastResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: CastResponse) {
                    view.hideLoading()
                    view.showActors(t.cast)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showActors(Collections.emptyList())
                }

            }))
    }

    override fun onDestory() {
        compositeDisposable.dispose()
    }
}