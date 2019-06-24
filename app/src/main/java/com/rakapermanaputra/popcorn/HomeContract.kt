package com.rakapermanaputra.popcorn

import com.rakapermanaputra.popcorn.model.*

interface HomeContract {

    interface View {
//        fun showLoading()
//        fun hideLoading()
        fun showAccountUser(userAccount: Account)
    }

    interface Presenter {
        fun getAccountUser(sessionId: String)
        fun onDestroy()
    }
}