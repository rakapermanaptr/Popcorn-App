package com.rakapermanaputra.popcorn.feature.login

import android.util.Log
import com.rakapermanaputra.popcorn.model.Login
import com.rakapermanaputra.popcorn.model.RequestToken
import com.rakapermanaputra.popcorn.model.Session
import com.rakapermanaputra.popcorn.model.Token
import com.rakapermanaputra.popcorn.model.repository.LoginRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class LoginPresenter(private val view: LoginContract.View,
                     private val loginRepoImpl: LoginRepoImpl)
    : LoginContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getReqToken() {
        view.showLoading()
        compositeDisposable.add(loginRepoImpl.getReqToken()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<Token>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: Token) {
                    view.showReqToken(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                }
            }))
    }

    override fun getToken(login: Login) {
        view.showLoading()
        compositeDisposable.add(loginRepoImpl.getToken(login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<Token>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: Token) {
                    view.showToken(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.d("Error", t?.message)
                }

            }))
    }

    override fun getSession(requestToken: RequestToken) {
        view.showLoading()
        compositeDisposable.add(loginRepoImpl.getSession(requestToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : ResourceSubscriber<Session>() {
                override fun onComplete() {
                    view.hideLoading()
                }

                override fun onNext(t: Session) {
                    view.showSessionId(t)
                }

                override fun onError(t: Throwable?) {
                    view.hideLoading()
                    Log.e("Error", "Session : " + t?.message)
                }

            }))
    }
}