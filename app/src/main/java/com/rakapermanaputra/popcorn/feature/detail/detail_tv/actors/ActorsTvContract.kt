package com.rakapermanaputra.popcorn.feature.detail.detail_tv.actors

import com.rakapermanaputra.popcorn.model.Cast

interface ActorsTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showActors(actors: List<Cast>)
    }

    interface Presenter {
        fun getActors(id: Int)
        fun onDestory()
    }
}