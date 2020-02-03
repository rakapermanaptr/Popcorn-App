package com.rakapermanaputra.popcorn.feature.watchlist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import com.rakapermanaputra.popcorn.feature.watchlist.movie.WatchlistMovieFragment
import com.rakapermanaputra.popcorn.feature.watchlist.tv.WatchlistTvFragment
import kotlinx.android.synthetic.main.fragment_watchlist.*


class WatchlistFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)
        val watchlistMovieFragment = WatchlistMovieFragment()
        val watchlistTvFragment = WatchlistTvFragment()
        adapter.populateFragment(watchlistMovieFragment, "Movies")
        adapter.populateFragment(watchlistTvFragment, "Tv Shows")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }


}
