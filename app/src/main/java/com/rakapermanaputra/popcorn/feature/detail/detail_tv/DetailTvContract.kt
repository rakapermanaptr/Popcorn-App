package com.rakapermanaputra.popcorn.feature.detail.detail_tv

import com.rakapermanaputra.popcorn.model.AddFavResponse
import com.rakapermanaputra.popcorn.model.DetailTv
import com.rakapermanaputra.popcorn.model.ReqFavBody
import com.rakapermanaputra.popcorn.model.TvShowsDetail

interface DetailTvContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDetail(dataDetail: TvShowsDetail)
        fun showMessage(addFavResponse: AddFavResponse)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun postFavTv(accoundId: Int, sessionId: String, reqFavBody: ReqFavBody)
        fun onDestroy()
    }
}