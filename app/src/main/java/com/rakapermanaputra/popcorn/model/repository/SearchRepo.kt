package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.SearchResponse
import io.reactivex.Flowable

interface SearchRepo {

    fun getSearch(queary: String): Flowable<SearchResponse>
}