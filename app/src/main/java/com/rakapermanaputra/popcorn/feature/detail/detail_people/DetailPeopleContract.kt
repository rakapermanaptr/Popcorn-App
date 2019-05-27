package com.rakapermanaputra.popcorn.feature.detail.detail_people

import com.rakapermanaputra.popcorn.model.PeopleDetail

interface DetailPeopleContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDetail(data: PeopleDetail)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun onDestroy()
    }
}