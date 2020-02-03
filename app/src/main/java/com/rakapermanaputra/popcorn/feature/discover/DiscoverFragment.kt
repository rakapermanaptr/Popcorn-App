package com.rakapermanaputra.popcorn.feature.discover


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import com.rakapermanaputra.popcorn.feature.discover.movie.DiscoverMoviesFragment
import com.rakapermanaputra.popcorn.feature.discover.tvshows.DiscoverTvshowsFragment
import kotlinx.android.synthetic.main.fragment_discover.*


class DiscoverFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)
        val discoverMoviesFragment = DiscoverMoviesFragment()
        val discoverTvshowsFragment = DiscoverTvshowsFragment()
        adapter.populateFragment(discoverMoviesFragment, "Movies")
        adapter.populateFragment(discoverTvshowsFragment, "Tv Shows")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }


}
