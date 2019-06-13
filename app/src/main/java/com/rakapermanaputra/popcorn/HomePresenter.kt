package com.rakapermanaputra.popcorn

import android.util.Log
import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.model.repository.LoginRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class HomePresenter(private val view: HomeContract.View,
                    private val loginRepoImpl: LoginRepoImpl)
    : HomeContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getAccountUser(sessionId: String) {
//        view.showLoading()
        compositeDisposable.add(loginRepoImpl.getAccount(sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<Account>() {
                override fun onComplete() {
//                    view.hideLoading()
                    Log.i("Data", "Complete")
                }

                override fun onNext(t: Account) {
                    view.showAccountUser(t)
                }

                override fun onError(t: Throwable?) {
//                    view.hideLoading()
                    Log.i("Error", t?.message)
                }

            }))
    }

    override fun getSession(requestToken: RequestToken) {
        compositeDisposable.add(loginRepoImpl.getSession(requestToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<Session>() {
                override fun onComplete() {
                    Log.d("Data", "Session complete")
                }

                override fun onNext(t: Session?) {
                    view.showSessionId(t)
                }

                override fun onError(t: Throwable) {
                    Log.e("Data", "Session error : " + t.message)
                }

            }))
    }


}