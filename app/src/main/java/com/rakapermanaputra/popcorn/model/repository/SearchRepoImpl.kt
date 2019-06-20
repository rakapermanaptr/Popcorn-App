package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.SearchResponse
import com.rakapermanaputra.popcorn.network.ApiRest
import io.reactivex.Flowable

class SearchRepoImpl(private val apiRest: ApiRest): SearchRepo {
    override fun getSearch(queary: String): Flowable<SearchResponse> = apiRest.getSearch(queary)
}