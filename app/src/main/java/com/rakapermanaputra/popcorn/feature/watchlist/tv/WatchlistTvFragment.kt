package com.rakapermanaputra.popcorn.feature.watchlist.tv


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.TvshowsAdapter
import com.rakapermanaputra.popcorn.db.SharedPreference
import com.rakapermanaputra.popcorn.model.TvShows
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_watchlist_tv.*


class WatchlistTvFragment : androidx.fragment.app.Fragment(), WatchlistTvContract.View {

    private lateinit var presenter: WatchlistTvPresenter
    private var watchlistTv: MutableList<TvShows> = mutableListOf()

    private lateinit var sharedPreference: SharedPreference
    private var accountId: Int = 0
    private lateinit var sessionId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreference = SharedPreference(requireContext())
        accountId = sharedPreference.getValueInt("ACCOUNT_ID")
        sessionId = sharedPreference.getValueString("SESSION_ID")!!

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = WatchlistTvPresenter(this, request)
        presenter.getAllWatchlist(accountId, sessionId)
    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showWatchlistTv(tvShows: List<TvShows>) {
        watchlistTv.clear()
        watchlistTv.addAll(tvShows)
        val linearLayoutManager =
            LinearLayoutManager(
                activity,
                RecyclerView.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = TvshowsAdapter(requireContext(), watchlistTv)
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllWatchlist(accountId, sessionId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist_tv, container, false)
    }


}
