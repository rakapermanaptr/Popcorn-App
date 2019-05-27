package com.rakapermanaputra.popcorn.feature.detail.detail_movie.reviews

import com.rakapermanaputra.popcorn.model.ReviewResponse
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class ReviewsMoviePresenter(private val view: ReviewsMovieContract.View,
                            private val detailMovieRepoImpl: DetailMovieRepoImpl)
    : ReviewsMovieContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getReviews(id: Int) {
        view.showLoading()
        compositeDisposable.add(detailMovieRepoImpl.getReview(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<ReviewResponse>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: ReviewResponse) {
                    view.showReviews(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showReviews(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}