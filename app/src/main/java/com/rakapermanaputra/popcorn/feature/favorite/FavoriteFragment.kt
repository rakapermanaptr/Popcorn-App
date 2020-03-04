package com.rakapermanaputra.popcorn.feature.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import com.rakapermanaputra.popcorn.feature.favorite.movies.FavMoviesFragment
import com.rakapermanaputra.popcorn.feature.favorite.tv.FavTvFragment
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : androidx.fragment.app.Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)
        val favMoviesFragment = FavMoviesFragment()
        val favTvFragment = FavTvFragment()
        adapter.populateFragment(favMoviesFragment, "Movies")
        adapter.populateFragment(favTvFragment, "Tv Shows")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }


}
