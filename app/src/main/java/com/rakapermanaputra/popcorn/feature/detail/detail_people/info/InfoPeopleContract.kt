package com.rakapermanaputra.popcorn.feature.detail.detail_people.info

import com.rakapermanaputra.popcorn.model.ImagesPeople
import com.rakapermanaputra.popcorn.model.PeopleDetail

interface InfoPeopleContract {

    interface View {
        fun showDetail(detail: PeopleDetail)
        fun showImages(images: List<ImagesPeople>)
    }

    interface Presenter {
        fun getDetail(id: Int)
        fun getImages(id: Int)
        fun onDestroy()
    }
}