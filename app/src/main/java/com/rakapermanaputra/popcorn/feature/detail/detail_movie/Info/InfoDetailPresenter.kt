package com.rakapermanaputra.popcorn.feature.detail.detail_movie.Info

import android.util.Log
import com.rakapermanaputra.popcorn.model.DetailMovie
import com.rakapermanaputra.popcorn.model.RecommendationMovieResponse
import com.rakapermanaputra.popcorn.model.SimilarMovieResponse
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class InfoDetailPresenter(private val view: InfoDetailContract.View,
                          private val detailMovieRepoImpl: DetailMovieRepoImpl)
    : InfoDetailContract.Presenter{

    val compositeDisposable = CompositeDisposable()

    override fun getDetail(id: Int) {
        compositeDisposable.add(detailMovieRepoImpl.getDetailMovie(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<DetailMovie>() {
                override fun onComplete() {
                    //TODO
                }

                override fun onNext(t: DetailMovie) {
                    view.showDetail(t)
                }

                override fun onError(t: Throwable?) {
                    //TODO
                    Log.e("Error", t?.message)
                }

            }))
    }


    override fun getSimilarMovies(id: Int) {
        compositeDisposable.add(detailMovieRepoImpl.getSimilarMovies(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<SimilarMovieResponse>() {
                override fun onComplete() {
                    Log.i("Data", "Complete")
                }

                override fun onNext(t: SimilarMovieResponse) {
                    view.showSimilarMovies(t.results)
                }

                override fun onError(t: Throwable?) {
                    Log.e("Error", t?.message)
                }

            }))
    }

    override fun getRecommendationMovies(id: Int) {
        compositeDisposable.add(detailMovieRepoImpl.getRecommendationMovies(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<RecommendationMovieResponse>() {
                override fun onComplete() {
                    Log.i("Data", "Complete")
                }

                override fun onNext(t: RecommendationMovieResponse) {
                    view.showRecommendation(t.results)
                }

                override fun onError(t: Throwable?) {
                    Log.e("Error", t?.message)
                }
            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}