package com.rakapermanaputra.popcorn.feature.login

import com.rakapermanaputra.popcorn.model.Login
import com.rakapermanaputra.popcorn.model.RequestToken
import com.rakapermanaputra.popcorn.model.Session
import com.rakapermanaputra.popcorn.model.Token

interface LoginContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showReqToken(token: Token)
        fun showToken(token: Token)
        fun showSessionId(session: Session)
    }

    interface Presenter {
        fun getReqToken()
        fun getToken(login: Login)
        fun getSession(requestToken: RequestToken)
    }

}
