package com.rakapermanaputra.popcorn.feature.tvshows


import android.os.Bundle
import android.support.v4.app.Fragment
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowsFragment : Fragment() {

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
