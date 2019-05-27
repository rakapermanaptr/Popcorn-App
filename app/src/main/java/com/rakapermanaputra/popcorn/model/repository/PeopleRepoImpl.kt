package com.rakapermanaputra.popcorn.model.repository

import com.rakapermanaputra.popcorn.model.ImagesPeopleResponse
import com.rakapermanaputra.popcorn.model.CreditsResponse
import com.rakapermanaputra.popcorn.model.PeopleDetail
import com.rakapermanaputra.popcorn.model.PeopleResponse
import com.rakapermanaputra.popcorn.network.ApiRest
import io.reactivex.Flowable

class PeopleRepoImpl(private val apiRest: ApiRest) : PeopleRepo {

    override fun getImagesPeople(id: Int): Flowable<ImagesPeopleResponse> = apiRest.getImagesPeople(id)

    override fun getMoviesCredits(id: Int): Flowable<CreditsResponse> = apiRest.getMovieCredits(id)

    override fun getDetailPeople(id: Int): Flowable<PeopleDetail> = apiRest.getDetailPeople(id)

    override fun getPopularPeoples(): Flowable<PeopleResponse> = apiRest.getPopularPeoples()
}