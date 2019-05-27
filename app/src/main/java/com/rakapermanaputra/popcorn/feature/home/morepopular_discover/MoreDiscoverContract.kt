package com.rakapermanaputra.popcorn.feature.home.morepopular_discover

import com.rakapermanaputra.popcorn.model.Movies

interface MoreDiscoverContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMoreDiscover(moreDiscover: List<Movies>)
    }

    interface Presenter {
        fun getMoreDiscover()
        fun onDestroy()
    }
}