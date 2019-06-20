package com.rakapermanaputra.popcorn.feature.search

import com.rakapermanaputra.popcorn.model.Search
import com.rakapermanaputra.popcorn.model.SearchResponse

interface SearchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showResultSearch(listResult: List<Search>)
    }

    interface Presenter {
        fun getResultSearch(query: String)
    }
}