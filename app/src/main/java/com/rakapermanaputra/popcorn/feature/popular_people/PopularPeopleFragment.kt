package com.rakapermanaputra.popcorn.feature.popular_people


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.PeopleAdapter
import com.rakapermanaputra.popcorn.model.People
import com.rakapermanaputra.popcorn.model.repository.PeopleRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_popular_people.*


class PopularPeopleFragment : Fragment(), PopularPeopleContract.View {

    private lateinit var presenter: PopularPeoplePresenter
    private val popularPeoples: MutableList<People> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = PeopleRepoImpl(service)
        presenter = PopularPeoplePresenter(this, request)
        presenter.getPopularPeoples()

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showPopularPeoples(peoples: List<People>) {
        popularPeoples.clear()
        popularPeoples.addAll(peoples)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = PeopleAdapter(requireContext(), popularPeoples)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_people, container, false)
    }


}
