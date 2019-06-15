package com.rakapermanaputra.popcorn.feature.detail.detail_movie

import com.rakapermanaputra.popcorn.model.AddFavResponse
import com.rakapermanaputra.popcorn.model.DetailMovie
import com.rakapermanaputra.popcorn.model.ReqFavBody

interface DetailContract {

    interface View {
        fun showBackdrop(detailMovie: DetailMovie)
        fun showPoster(detailMovie: DetailMovie)
        fun showDetail(detailMovie: DetailMovie)
        fun showLoading()
        fun hideLoading()
        fun showMessage(addFavResponse: AddFavResponse)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun postFavMovie(accounId: Int, sessionId: String, reqFavBody: ReqFavBody)
        fun onDestroy()
    }
}