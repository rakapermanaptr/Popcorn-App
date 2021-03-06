package com.rakapermanaputra.popcorn.feature.tvshows


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import com.rakapermanaputra.popcorn.feature.tvshows.airing_today.TvshowsAiringTodayFragment
import com.rakapermanaputra.popcorn.feature.tvshows.on_the_air.TvshowsOnTheAirFragment
import com.rakapermanaputra.popcorn.feature.tvshows.popular.TvshowsPopularFragment
import com.rakapermanaputra.popcorn.feature.tvshows.top_rated.TvshowsTopRatedFragment
import kotlinx.android.synthetic.main.fragment_tv_shows.*


class TvShowsFragment : androidx.fragment.app.Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)
        val tvshowsAiringTodayFragment = TvshowsAiringTodayFragment()
        val tvshowsOnTheAirFragment = TvshowsOnTheAirFragment()
        val tvshowsPopularFragment = TvshowsPopularFragment()
        val tvshowsTopRatedFragment = TvshowsTopRatedFragment()
        adapter.populateFragment(tvshowsAiringTodayFragment, "Airing Today")
        adapter.populateFragment(tvshowsOnTheAirFragment, "On The Air")
        adapter.populateFragment(tvshowsPopularFragment, "Popular")
        adapter.populateFragment(tvshowsTopRatedFragment, "Top Rated")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
    }


}
