package com.rakapermanaputra.popcorn.feature.detail.detail_people.tvshows


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.TvCreditsAdapter
import com.rakapermanaputra.popcorn.model.Credits
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_tvshows_people.*


class TvshowsPeopleFragment : androidx.fragment.app.Fragment(), TvCreditsContract.View {

    private lateinit var presenter: TvCreditsPresenter
    private var tvshowsCredits: MutableList<Credits> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = arguments!!.getInt("id")

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = TvCreditsPresenter(this, request)
        presenter.getTvCredits(id)

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showTvCredits(tvCredits: List<Credits>) {
        tvshowsCredits.clear()
        tvshowsCredits.addAll(tvCredits)
        val linearLayoutManager =
            LinearLayoutManager(
                activity,
                RecyclerView.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = TvCreditsAdapter(requireContext(), tvshowsCredits)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows_people, container, false)
    }


}
