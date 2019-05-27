package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.ImagesPeopleResponse
import com.rakapermanaputra.popcorn.model.CreditsResponse
import com.rakapermanaputra.popcorn.model.PeopleDetail
import com.rakapermanaputra.popcorn.model.PeopleResponse
import io.reactivex.Flowable

interface PeopleRepo {

    fun getPopularPeoples(): Flowable<PeopleResponse>

    fun getDetailPeople(id: Int): Flowable<PeopleDetail>

    fun getMoviesCredits(id: Int) : Flowable<CreditsResponse>

    fun getImagesPeople(id: Int): Flowable<ImagesPeopleResponse>

}