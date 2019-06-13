package com.rakapermanaputra.popcorn.feature.movies.nowplaying

import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class MoviesNowPlayingPresenter(private val view: MoviesNowPlayingContract.View,
                                private val moviesRepoImpl: MoviesRepoImpl)
    : MoviesNowPlayingContract.Presenter {

    val compositeDisposable = CompositeDisposable()

//    override fun getNowPlaying(page: Int) {
//        view.showLoading()
//        compositeDisposable.add(moviesRepoImpl.getNowPlaying(page)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(object : ResourceSubscriber<MoviesResponse>(){
//                override fun onComplete() {
//                    view.hideLoading()
//                }
//
//                override fun onNext(t: MoviesResponse) {
//                    view.showNowPlaying(t.results)
//                }
//
//                override fun onError(t: Throwable?) {
//                    view.hideLoading()
//                    view.showNowPlaying(Collections.emptyList())
//                }
//
//            })
//        )
//    }


    override fun getNowPlaying() {
        view.showLoading()
        compositeDisposable.add(moviesRepoImpl.getNowPlaying()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>(){
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: MoviesResponse) {
                    view.showNowPlaying(t.results)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    view.showNowPlaying(Collections.emptyList())
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}