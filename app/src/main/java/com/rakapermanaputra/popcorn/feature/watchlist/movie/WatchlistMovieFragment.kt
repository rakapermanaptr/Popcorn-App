package com.rakapermanaputra.popcorn.feature.watchlist.movie


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.MoviesAdapter
import com.rakapermanaputra.popcorn.db.SharedPreference
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_watchlist_movie.*


class WatchlistMovieFragment : androidx.fragment.app.Fragment(), WatchlistMovieContract.View {

    private lateinit var presenter: WatchlistMoviePresenter
    private var watchlistMovies: MutableList<Movies> = mutableListOf()

    private lateinit var sharedPreference: SharedPreference
    private var accountId: Int = 0
    private lateinit var sessionId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreference = SharedPreference(requireContext())
        accountId = sharedPreference.getValueInt("ACCOUNT_ID")
        sessionId = sharedPreference.getValueString("SESSION_ID")!!

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MoviesRepoImpl(service)
        presenter = WatchlistMoviePresenter(this, request)
        presenter.getAllWatchlist(accountId, sessionId)
    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoding() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showAllWatchlist(movies: List<Movies>) {
        watchlistMovies.clear()
        watchlistMovies.addAll(movies)
        val linearLayoutManager =
            LinearLayoutManager(
                activity,
                RecyclerView.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = MoviesAdapter(requireContext(), watchlistMovies)
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
        return inflater.inflate(R.layout.fragment_watchlist_movie, container, false)
    }


}
