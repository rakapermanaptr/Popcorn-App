package com.rakapermanaputra.popcorn.feature.movies


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import com.rakapermanaputra.popcorn.feature.movies.nowplaying.MoviesNowPlayingFragment
import com.rakapermanaputra.popcorn.feature.movies.popular.MoviesPopularFragment
import com.rakapermanaputra.popcorn.feature.movies.top_rated.MoviesTopRatedFragment
import com.rakapermanaputra.popcorn.feature.movies.upcoming.MoviesUpcomingFragment
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : androidx.fragment.app.Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)
        val movieNowPlayingFragment = MoviesNowPlayingFragment()
        val moviesPopularFragment = MoviesPopularFragment()
        val moviesTopRatedFragment = MoviesTopRatedFragment()
        val moviesUpcomingFragment = MoviesUpcomingFragment()
        adapter.populateFragment(movieNowPlayingFragment, "Now Playing")
        adapter.populateFragment(moviesPopularFragment, "Popular")
        adapter.populateFragment(moviesTopRatedFragment, "Top Rated")
        adapter.populateFragment(moviesUpcomingFragment, "Upcoming")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }


}
