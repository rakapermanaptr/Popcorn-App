package com.rakapermanaputra.popcorn.feature.tvshows.popular


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
import kotlinx.android.synthetic.main.fragment_tvshows_popular.*


class TvshowsPopularFragment : androidx.fragment.app.Fragment(),
    TvshowsPopularContract.View {

    private lateinit var presenter: TvshowsPopularPresenter
    private val popularTvshows: MutableList<TvShows> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = TvshowsPopularPresenter(this, request)
        presenter.getPopular()

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showPopular(tvshows: List<TvShows>) {
        popularTvshows.clear()
        popularTvshows.addAll(tvshows)
        val linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                activity,
                LinearLayout.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = TvshowsAdapter(requireContext(), popularTvshows)
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
        return inflater.inflate(R.layout.fragment_tvshows_popular, container, false)
    }


}
