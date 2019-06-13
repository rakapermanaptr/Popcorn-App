package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.network.ApiRest
import io.reactivex.Flowable

class LoginRepoImpl(private val apiRest: ApiRest) : LoginRepo {
    override fun getAccount(sessionId: String): Flowable<Account> = apiRest.getAccount(sessionId)
    override fun getSession(requestToken: RequestToken): Flowable<Session> = apiRest.getSession(requestToken)
    override fun getToken(login: Login): Flowable<Token> = apiRest.getToken(login)
    override fun getReqToken(): Flowable<Token> = apiRest.getReqToken()
}