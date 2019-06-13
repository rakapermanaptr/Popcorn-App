package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.*
import io.reactivex.Flowable

interface LoginRepo {

    fun getReqToken(): Flowable<Token>

    fun getToken(login: Login): Flowable<Token>

    fun getSession(requestToken: RequestToken): Flowable<Session>

    fun getAccount(sessionId: String): Flowable<Account>
}