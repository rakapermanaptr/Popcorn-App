package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.Login
import com.rakapermanaputra.popcorn.model.RequestToken
import com.rakapermanaputra.popcorn.model.Session
import com.rakapermanaputra.popcorn.model.Token
import io.reactivex.Flowable

interface LoginRepo {

    fun getReqToken(): Flowable<Token>

    fun getToken(login: Login): Flowable<Token>

    fun getSession(requestToken: RequestToken): Flowable<Session>
}