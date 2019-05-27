package com.rakapermanaputra.popcorn.feature.detail.detail_movie.cast

import com.rakapermanaputra.popcorn.model.Cast

interface CastMovieContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showCaster(caster: List<Cast>)
    }

    interface Presenter {
        fun getCaster(id: Int)
        fun onDestroy()
    }
}