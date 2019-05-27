package com.rakapermanaputra.popcorn.feature.detail.detail_people.movies

import com.rakapermanaputra.popcorn.model.CreditsResponse
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class MovieCreditsPresenter(private val view: MovieCreditsContract.View,
                            private val tvShowsRepoImpl: TvShowsRepoImpl)
    : MovieCreditsContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getMovieCredits(id: Int) {
        view.showLoading()
        compositeDisposable.add(tvShowsRepoImpl.getMovieCredits(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<CreditsResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: CreditsResponse) {
                    view.showMovieCredits(t.cast)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showMovieCredits(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}