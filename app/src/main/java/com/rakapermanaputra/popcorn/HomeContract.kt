package com.rakapermanaputra.popcorn

import com.rakapermanaputra.popcorn.model.*

interface HomeContract {

    interface View {
//        fun showLoading()
//        fun hideLoading()
        fun showAccountUser(userAccount: Account)
        fun showSessionId(session: Session?)
    }

    interface Presenter {
        fun getAccountUser(sessionId: String)
        fun getSession(requestToken: RequestToken)
    }
}