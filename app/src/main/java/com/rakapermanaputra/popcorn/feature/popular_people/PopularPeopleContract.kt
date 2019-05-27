package com.rakapermanaputra.popcorn.feature.popular_people

import com.rakapermanaputra.popcorn.model.People

interface PopularPeopleContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showPopularPeoples(peoples: List<People>)
    }

    interface Presenter {
        fun getPopularPeoples()
        fun onDestroy()
    }
}