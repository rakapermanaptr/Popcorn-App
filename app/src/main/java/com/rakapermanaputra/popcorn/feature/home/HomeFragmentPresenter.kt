package com.rakapermanaputra.popcorn.feature.home

import android.util.Log
import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class HomeFragmentPresenter(private val view: HomeFragmentContract.View,
                            private val movieRepoImpl: MoviesRepoImpl,
                            private val tvShowsRepoImpl: TvShowsRepoImpl)
    : HomeFragmentContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getNowPlayingMovies() {
        compositeDisposable.add(movieRepoImpl.getNowPlaying()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    Log.i("Data nowplaying" , "Complate")
                }

                override fun onNext(t: MoviesResponse) {
                    view.showNowPlayingMovies(t.results)
                }

                override fun onError(t: Throwable) {
                    Log.e("Error", t.message)
                }

            }))
    }

    override fun getPopularMovies() {
        compositeDisposable.add(movieRepoImpl.getPopular()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    Log.i("Data popular movies" , "Complate")
                }

                override fun onNext(t: MoviesResponse) {
                    view.showPopularMovies(t.results)
                }

                override fun onError(t: Throwable) {
                    Log.e("Error", t.message)
                }

            }))
    }

    override fun getPopularTvs() {
        compositeDisposable.add(tvShowsRepoImpl.getPopular()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TvShowsResponse>() {
                override fun onComplete() {
                    Log.i("Data popular tvs" , "Complate")
                }

                override fun onNext(t: TvShowsResponse) {
                    view.showPopularTvs(t.results)
                }

                override fun onError(t: Throwable) {
                    Log.e("Error", t.message)
                }

            }))
    }

    override fun getDiscoverMovies() {
        compositeDisposable.add(movieRepoImpl.getDiscoverMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<MoviesResponse>() {
                override fun onComplete() {
                    Log.i("Data discover" , "Complate")
                }

                override fun onNext(t: MoviesResponse) {
                    view.showDiscoverMovies(t.results)
                }

                override fun onError(t: Throwable) {
                    Log.e("Error", t.message)
                }

            }))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}