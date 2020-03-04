package com.rakapermanaputra.popcorn.feature.discover.tvshows


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.TvshowsAdapter
import com.rakapermanaputra.popcorn.model.TvShows
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_discover_tvshows.*


class DiscoverTvshowsFragment : androidx.fragment.app.Fragment(), DiscoverTvshowsContract.View {

    private lateinit var presenter: DiscoverTvshowsPresenter
    private val discoverTvshows: MutableList<TvShows> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = DiscoverTvshowsPresenter(this, request)

        presenter.getDiscoverTvshows()
    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showDiscoverTvshows(discover: List<TvShows>) {
        discoverTvshows.clear()
        discoverTvshows.addAll(discover)
        val linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                activity,
                LinearLayout.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = TvshowsAdapter(requireContext(), discoverTvshows)
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
        return inflater.inflate(R.layout.fragment_discover_tvshows, container, false)
    }


}
